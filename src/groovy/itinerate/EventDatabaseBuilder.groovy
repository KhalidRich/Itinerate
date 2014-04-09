package itinerate

import itinerate.plan.Event
import itinerate.place.Address

def databaseFile = new File("../../Database Categories.txt")

def addTags(event, tags)
{
    tags.each {
        if (!it.trim().isEmpty())
            event.addToTags(it.toLowerCase())
    }
}

def lineNo = 0
def columnIndex = [:]
def row = 0
def event = new Event()
databaseFile.eachLine {
    def columns = it.split("\t")
    if (columns.length > 1) {
        if (lineNo != 0 && !columns[1].trim().isEmpty()) {
            // Check its name. If its "^", then don't do a bunch of stuff
            if (!columns[1].equals("^")) {
                event = new Event()
                event.name = columns[1]

                // Assign the tags
                addTags(event, columns[2..5])
                // Assign the address
                if (!columns[2].trim().isEmpty()) {
                    Address address = Address.getAddressFromString(columns[2])
                    if (!address.isAttached)
                        address.attach()
                    event.address = address
                }
            }
        }
    }
    lineNo++
}
println event.address
