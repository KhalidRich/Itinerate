package itinerate

import itinerate.place.Event
import itinerate.place.Address
import itinerate.place.Category
import itinerate.place.Price
import itinerate.place.Hours
import itinerate.place.OperationTime
import itinerate.plan.Holiday
import itinerate.plan.HolidayTime
import itinerate.plan.DayOfWeek
import itinerate.place.EventType

def databaseFile = new File("updated-database.txt")

def addTags(event, tags)
{
    tags.each {
        if (!it.trim().isEmpty())
            event.addToCategories(Category.getEnumFromName(it))
    }
}

def addPrice(event, prices)
{
    def money = ~/\$(\d+(\.\d+)?)/
    def free = ~/((F|f)ree)/
    def ageRange = ~/\((\d+-\d+)\)/
    def specialAgeRange = ~/(\((Below \d+|\d+ and below)\))/
    def priceList = ["adultPrice", "childPrice", "specialChildPrice", "studentPrice", "seniorPrice"]
    def price = new Price()
    def curPrice = 0
    // Go through each potential price
    prices.each {
        def monMatch = it =~ money
        // Figure out the price
        if (it.trim().isEmpty())
            price."${priceList[curPrice]}" = -2
        else if (monMatch.size() > 0)
            price."${priceList[curPrice]}" = new BigDecimal(monMatch[0][1])
        // Check to see if the price is free
        def freeMatch = it ==~ free
        if (freeMatch)
            price."${priceList[curPrice]}" = 0
        // Check for any child restrictions
        if (curPrice == 1) {
            def ageMatcher = it =~ ageRange
            if (ageMatcher.size() > 0)
                price.childRange = ageMatcher[0][1]
        }
        // Check for special child restrictions
        if (curPrice == 2) {
            def specialMatcher = it =~ specialAgeRange
            if (specialMatcher.size() > 0)
                price.specialChildRange = specialMatcher[0][2]
        }
        curPrice++
    }
    event.pricing = price
    /**priceList.each {
        print "|"
        print price."$it"
        print "|"
    }
    println ""**/
}

def getTime(timeString)
{
    // println timeString
    int time = 0
    def timeTok = timeString.split("\\ ")
    def timeParts = timeTok[0].split(":");
    time += timeParts[0].toInteger() * 100
    if (timeParts.length > 1)
        time += timeParts[1].toInteger()
    if (timeTok[1].equals("PM"))
        time += 1200
    // println time
    return time
}

def addOperations(event, startMonth, endMonth, times)
{
    def operation = new OperationTime(startMonth: startMonth, endMonth: endMonth)
    for (int i = 0; i < times.size(); i += 2) {
        def hours = new Hours()
        // Check the start time
        if (times[i].equals("Closed") || times[i].equals("Always"))
            hours.startTime = 0
        else if (!times[i].trim().isEmpty())
            hours.startTime = getTime(times[i])
        // Check the close time
        if (times[i + 1].equals("Closed"))
            hours.endTime = 0
        else if (times[i + 1].equals("Always"))
            hours.endTime = 2400
        else if (!times[i + 1].trim().isEmpty())
            hours.endTime = getTime(times[i + 1])
        // Set the day of week
        hours.day = DayOfWeek.values()[((int) (i / 2) + 1) % 7]
        operation.addToHours(hours)
    }
    event.addToOperations(operation)
    return operation
}

def addHolidays(operation, holidays)
{
    holidays.each {
        if (!it.trim().isEmpty()) {
            def holiday = Holiday.getHolidayFromString(it)
            if (holiday != null) {
                // Check for a closing time
                def closingTime = ~/\d+ (PM|AM)/
                def closeMatcher = it =~ closingTime
                def time = null
                if (closeMatcher.size() > 0)
                    time =  getTime(closeMatcher[0][0])
                operation.addToHolidays(new HolidayTime(day: holiday, closingTime: time))
            }
        }
    }
}

def lineNo = 0
def columnIndex = [:]
def row = 0
def event
databaseFile.eachLine {
    def columns = it.split("\t")
    if (columns.length > 1) {
        if (lineNo != 0 && !columns[1].trim().isEmpty()) {
            // Check its name. If its "^", then don't do a bunch of stuff
            if (!columns[1].equals("^")) {
                event = Event.findByName(columns[1])
                // If there's no event by this name, then don't run this script on it
                if (event != null) {
                    println event.name
                    println columns[3..6]
                    def tags = []
                    columns[3..6].each {
                        def cat = Category.getEnumFromName(it)
                        if (cat != null)
                            tags += cat
                    }
                    println tags
                    println event.categories
                    if (event.categories == null) {
                        tags.each {
                            event.addToCategories(it)
                        }
                    } else {
                        event.categories.clear()
                        tags.each {
                            event.addToCategories(it)
                        }
                    }
                    println event.categories
                    // Cleanup ticket exceptions
                    if (event.ticketExceptions != null) {
                        for (int i = 0; i < event.ticketExceptions.size(); i++) {
                            if (event.ticketExceptions.get(i).trim().equals("")) {
                                event.ticketExceptions.remove(i)
                                i--
                            }
                        }
                    }
                    event.type = EventType.ATTRACTION
                    event.save()
                } else
                    System.err << "${columns[1]} is not in the database!"
            } else if (columns[1].equals("^") && event != null) {

                // event.save()
            }
        }
    }
    lineNo++
}
