package itinerate

import itinerate.place.Event
import itinerate.place.Address
import itinerate.place.Category
import itinerate.place.Price
import itinerate.place.Hours
import itinerate.place.OperationTime
import itinerate.plan.Holiday
import itinerate.plan.HolidayTime

def databaseFile = new File("Database-Categories-unicode.txt")

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
                if (closeMatcher.size() > 0)
                    println "${closeMatcher[0]}"
                operation.addToHolidays(new HolidayTime(day: holiday))
            }
        }
    }
}

def lineNo = 0
def columnIndex = [:]
def row = 0
def event = new Event()
databaseFile.eachLine {
    def columns = it.split("\t")
    /**if (columns.length > 1 && !columns[1].trim().isEmpty()) {
        def col = 0
        print "["
        columns.each {
            print "|$col:$it|"
            col++
        }
        println "]"
    }**/
    if (columns.length > 1) {
        if (lineNo != 0 && !columns[1].trim().isEmpty()) {
            // Check its name. If its "^", then don't do a bunch of stuff
            if (!columns[1].equals("^")) {
                event = new Event()
                event.name = columns[1]

                // Assign the tags
                addTags(event, columns[2..5])
                // Assign the address
                event.address = columns[6..8].toString()
                // Assign the zip code
                try {
                    event.zipCode = columns[9].toInteger()
                } catch (e) {}
                // Assign the telephone number
                event.telephoneNumber = columns[10]
                // Assign the website
                event.website = columns[11]
                // Assign the stay time
                try {
                    event.recommendedStayTime = columns[12].toInteger()
                } catch (e) {}
                // Assign the prices
                addPrice(event, columns[13..17])
                // Assign the hours
                def operation = addOperations(event, columns[18], columns[19], columns[20..33])
                // Assign the holidays
                addHolidays(operation, columns[34..43])
                // Assign the ticket requirement
                if (columns[47].equals("Yes"))
                    event.ticketsRequired = 1
                else if (columns[47].equals("No"))
                    event.ticketsRequired = 0
                else
                    event.ticketsRequired = 2
                // Assign the ticket exception
                event.addToTicketExceptions(columns[48])
                // Finally, the ticket inks
                event.ticketLink = columns[49]
            } else if (columns[1].equals("^")) {
                // Assign the hours
                def operation = addOperations(event, columns[18], columns[19], columns[20..33])
                // Assign the holidays
                addHolidays(operation, columns[34..43])
            }
        }
    }
    lineNo++
}
