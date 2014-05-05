package itinerate

import itinerate.place.Category
import itinerate.place.Event
import itinerate.place.EventType
import itinerate.place.Hours
import itinerate.place.OperationTime
import itinerate.place.Price
import itinerate.plan.DayOfWeek
import itinerate.plan.Holiday
import itinerate.plan.HolidayTime

def databaseFile = new File("foodAndHotels.txt")

def addTags(event, tags) {
    tags.each {
        if (!it.trim().isEmpty())
            event.addToCategories(Category.getEnumFromName(it))
    }
}

def addPrice(event, prices) {
    def money = ~/\$(\d+(\.\d+)?)/
    def free = ~/((F|f)ree)/
    def ageRange = ~/\((\d+-\d+)\)/
    def specialAgeRange = ~/(\((Below \d+|\d+ and below)\))/
    def priceList = [
        "adultPrice",
        "childPrice",
        "specialChildPrice",
        "studentPrice",
        "seniorPrice"
    ]
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
    def timeParts = timeTok[0].split(":")
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

def parsePrice(dollar)
{
    def price = new Price()
    if (dollar.equals("\$"))
        price.adultPrice = price.childPrice = price.specialChildPrice = price.studentPrice = price.seniorPrice = -3
    else if (dollar.equals("\$\$"))
        price.adultPrice = price.childPrice = price.specialChildPrice = price.studentPrice = price.seniorPrice = -4
    else if (dollar.equals("\$\$\$"))
        price.adultPrice = price.childPrice = price.specialChildPrice = price.studentPrice = price.seniorPrice = -5
    return price
}

def parseHours(hours)
{
    def time = new OperationTime()
    time.startMonth = "January"
    time.endMonth = "December"
    buildHours(time, hours)
    return time
}

def getDay(day)
{
    def Sunday = ~/Su(n)?/
    def Monday = ~/M/
    def Tuesday = ~/T(u)?/
    def Thursday = ~/Th(urs)?/
    def Friday = ~/F(ri)?/
    def Saturday = ~/Sat/
    if (day ==~ Sunday)
        return DayOfWeek.SUNDAY
    if (day ==~ Monday)
        return DayOfWeek.MONDAY
    if (day ==~ Tuesday)
        return DayOfWeek.TUESDAY
    if (day ==~ Thursday)
        return DayOfWeek.THURSDAY
    if (day ==~ Friday)
        return DayOfWeek.FRIDAY
    if (day ==~ Saturday)
        return DayOfWeek.SATURDAY
    return null
}

def getTime2(timeMatcher)
{
    def startTime = Integer.parseInt(timeMatcher[0][1]) * 100
    if (timeMatcher[0][3] != null)
        startTime += Integer.parseInt(timeMatcher[0][3])
    if (timeMatcher[0][4] != null && timeMatcher[0][4].equals("p"))
        startTime += 1200
    return startTime
}

def getTimes(timeRange)
{
    def pmPat = ~/(\d+)(\:(\d+))?(p)/
    def amPat = ~/(\d+)(\:(\d+))?(am)?/
    // Get the open times
    def timeArr = timeRange.split("\\;")
    // println "timeArr: $timeArr"
    // There are times in the day when it is closed
    if (timeArr.length > 1) {
        def firstRange = timeArr[0].split("\\-")
        def secondRange = timeArr[1].split("\\-")
        def startTime
        def endTime
        def breakStartTime
        def breakEndTime
        // println "firstRange: ${firstRange}"
        if (firstRange[0] ==~ pmPat) {
            startTime = getTime2(firstRange[0] =~ pmPat)
            // println "startTime: $startTime"
        } else if (firstRange[0] ==~ amPat) {
            startTime = getTime2(firstRange[0] =~ amPat)
            // println "startTime: $startTime"
        }
        if (firstRange[1] ==~ pmPat) {
            breakStartTime = getTime2(firstRange[1] =~ pmPat)
            // println "breakStartTime: $breakStartTime"
        } else if (firstRange[1] ==~ amPat) {
            breakStartTime = getTime2(firstRange[1] =~ amPat)
            // println "breakStartTime: $breakStartTime"
        }
        // println "secondRange: ${secondRange}"
        if (secondRange[1] ==~ pmPat) {
            endTime = getTime2(secondRange[1] =~ pmPat)
            // println "endTime: $endTime"
        } else if (secondRange[1] ==~ amPat) {
            endTime = getTime2(secondRange[1] =~ amPat)
            // println "endTime: $endTime"
        }
        if (secondRange[0] ==~ pmPat) {
            breakEndTime = getTime2(secondRange[0] =~ pmPat)
            // println "breakEndTime: $breakEndTime"
        } else if (secondRange[0] ==~ amPat) {
            breakEndTime = getTime2(secondRange[0] =~ amPat)
            // println "breakEndTime: $breakEndTime"
        }
        return [
            startTime,
            endTime,
            breakStartTime,
            breakEndTime
        ]
    } else {
        def firstRange = timeArr[0].split("\\-")
        def startTime
        def endTime
        // println "firstRange: ${firstRange}"
        if (firstRange[0] ==~ pmPat) {
            startTime = getTime2(firstRange[0] =~ pmPat)
            // println "startTime: $startTime"
        } else if (firstRange[0] ==~ amPat) {
            startTime = getTime2(firstRange[0] =~ amPat)
            // println "startTime: $startTime"
        }
        if (firstRange[1] ==~ pmPat) {
            endTime = getTime2(firstRange[1] =~ pmPat)
            // println "endTime: $endTime"
        } else if (firstRange[1] ==~ amPat) {
            endTime = getTime2(firstRange[1] =~ amPat)
            // println "endTime: $endTime"
        }
        return [startTime, endTime]
    }
}

def buildHours(time, hours)
{
    hourToks = hours.replaceAll("\"", "").split("\\,\\ ")
    hourToks.each {
        if (!it.contains("Closed")) {
            it = it.replaceAll(";\\ ", ";").replaceAll("(\\ )?\\-(\\ )?", "\\-")
            // If it starts with open, then just fill all days
            if (it.startsWith("Open")) {
                for (DayOfWeek day : DayOfWeek.values()) {
                    def hourObj = new Hours()
                    hourObj.startTime = 0
                    hourObj.endTime = 2400
                    hourObj.day = day
                    time.addToHours(hourObj)
                }
            } else {
                // Split on space, day and time
                def openTok = it.split("\\ ")
                // If it contains a "-", it is a range
                if (openTok[0].contains("-")) {
                    def range = openTok[0].split("\\-")
                    def startDate = getDay(range[0])
                    def endDate = getDay(range[1])
                    def times = getTimes(openTok[1])
                    println "times: $times"
                    // Go through the range
                    int curDate = startDate.ordinal()
                    while (curDate != endDate.ordinal()) {
                        def date = DayOfWeek.values()[curDate]
                        if (times.size() == 2)
                            time.addToHours(new Hours(day: date, startTime: times.get(0), endTime: times.get(1)))
                        else if (times.size() == 4) {
                            def hourObj = new Hours()
                            hourObj.day = date
                            hourObj.startTime = times[0]
                            hourObj.endTime = times[1]
                            hourObj.addToExceptTimes(times[2])
                            hourObj.addToExceptTimes(times[3])
                            time.addToHours(hourObj)
                        }
                        curDate = (curDate + 1) % DayOfWeek.values().length
                    }
                } else {
                    def date = getDay(openTok[0])
                    def times = getTimes(openTok[1])
                    println "times: $times, ${times.size()}"
                    if (times.size() == 2)
                        time.addToHours(new Hours(day: date, startTime: times.get(0), endTime: times.get(1)))
                    else if (times.size() == 4) {
                        def hourObj = new Hours()
                        hourObj.day = date
                        hourObj.startTime = times[0]
                        hourObj.endTime = times[1]
                        hourObj.addToExceptTimes(times[2])
                        hourObj.addToExceptTimes(times[3])
                        time.addToHours(hourObj)
                    }
                }
            }
        }
    }
}

def lineNo = 0
def columnIndex = [:]
def row = 0
def event
def hotel = false
databaseFile.eachLine {
    def columns = it.split("\t")
    if (columns.length > 1) {
        if (lineNo != 0 && !columns[0].trim().isEmpty()) {
            // Check its name. If its "^", then don't do a bunch of stuff
            if (!columns[0].equals("^")) {
                event = new Event()
                // Assign its name
                event.name = columns[0]
                if (!columns[1].trim().isEmpty())
                    event.description = columns[1]
                event.address = columns[2].replaceAll("\"", "")
                event.telephoneNumber = columns[3]
                if (!hotel) {
                    event.type = EventType.FOOD
                    // Assign its hours
                    event.addToOperations(parseHours(columns[4]))
                    // Make a price object
                    event.pricing = parsePrice(columns[5])
                    // Set the picture source
                    event.addToPictureSources(columns[6])
                } else {
                    event.type = EventType.ACCOMMODATION
                }
                if (columns.length > 6)
                    println it
            } else if (columns[0].equals("^")) {

            }
        }
    } else if (it.trim().isEmpty())
        hotel = true
    lineNo++
}
