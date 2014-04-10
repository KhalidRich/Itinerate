package itinerate

import itinerate.place.Event
import itinerate.place.Address
import itinerate.place.Category

def databaseFile = new File("Database Categories.csv")

def addTags(event, tags)
{
    tags.each {
        if (!it.trim().isEmpty())
            event.addToCategories(Category.getEnumFromName(it))
    }
}

def addPrice(event, prices)
{
    def money = ~/((\$)?\d+(\.\d+)?)/
    def free = ~/((F|f)ree)/
    def ageRange = ~/(\(\d+-\d+\))/
    def specialAgeRange = ~/(\((Below|\d+)(\d+|and below)\))/
    def curPrice = ["adultPrice", "childPrice", "specialChildPrice", "studentPrice", "seniorPrice"]
    prices.each {
        def monMatch = it =~ money
    }
}

def lineNo = 0
def columnIndex = [:]
def row = 0
def event = new Event()
databaseFile.eachLine {
    def columns = it.split(",")
    if (columns.length > 1) {
        if (lineNo != 0 && !columns[1].trim().isEmpty()) {
            // Check its name. If its "^", then don't do a bunch of stuff
            if (!columns[1].equals("^")) {
                event = new Event()
                event.name = columns[1]

                // Assign the tags
                addTags(event, columns[2..5])
                // Assign the address
                event.address = columns[6]
                // Assign the zip code
                try {
                    event.zipCode = columns[7].toInteger()
                } catch (e) {}
                // Assign the telephone number
                event.telephoneNumber = columns[8]
                // Assign the website
                event.website = columns[9]
                // Assign the stay time
                event.recommendedStayTime = columns[10].toInteger()
                // Assign the prices
                addPrice(event, columns[11..15])
            }
        }
    }
    lineNo++
}
println event.address
