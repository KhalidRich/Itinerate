package itinerate.plan

import groovy.time.*
import itinerate.User
import itinerate.UserFunctions
import itinerate.place.Event

import java.text.DateFormat
import java.text.SimpleDateFormat

class Itinerary {
    String name
    String location = "Philadelphia" // no other location for now

    Date createdAt = new Date()

    List days

    public static Integer buildItinerary(String eventsParam, Long userId, String name)
    {
        DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH)
        println eventsParam
        // eventsParam = "Philadelphia Museum of Art;Fri Apr 11 2014 06:30:00 GMT-0400 (EDT);null|"
        // userId = 1
        if (eventsParam == null || eventsParam.trim().isEmpty())
            return -1
        else if (userId == null || userId <= 0)
            return -6
        // First, parse the itinerary
        def events = eventsParam.split("\\|")
        if (events.length < 1)
            return -2
        // Make an itinerary object
        def itinerary = new Itinerary(name: name)

        // Iterate over each event
        events.each {
            def itinItem = new ItineraryItem()
            def eventDetails = it.split(";")
            // Details come in groups of three
            if (eventDetails.length == 3) {
                // Get the event
                def event = Event.findByName(eventDetails[0])
                if (event != null) {
                    itinItem.event = event
                    println event
                    // Get the start date
                    itinItem.startTime = df.parse(eventDetails[1].substring(0,24))
                    println itinItem.startTime
                    // Get the end date
                    if (eventDetails[2].equals("null")) {
                        use( [groovy.time.TimeCategory]){
                            itinItem.endTime = itinItem.startTime + 2.hours
                            println itinItem.endTime
                        }
                    } else {
                        itinItem.endTime = df.parse(eventDetails[2].substring(0,24))
                        println itinItem.endTime
                    }
                    // We're ready to add the event to the day
                    def added = false
                    def itinGregor = itinItem.startTime.toCalendar()
                    itinerary.days.each {
                        def dayGregor = it.dayDate.toCalendar()
                        // Make sure they are the same day of the same year
                        if (dayGregor.DAY_OF_YEAR == itinGregor.DAY_OF_YEAR && dayGregor.YEAR == itinGregor.YEAR) {
                            // We have a match, add this event to this day
                            it.addToEvents(event)
                            it.addToItems(itinItem)
                            added = true
                        }
                    }
                    if (!added) {
                        def day = new Day(dayDate: df.parse(eventDetails[1].substring(0,24)))
                        day.addToEvents(event)
                        day.addToItems(itinItem)
                        itinerary.addToDays(day)
                    }
                }
            }
        }

        // We now have a built itinerary, let's add it to the user
        def user = User.get(userId)
        if (user == null)
            return -3
        else if (UserFunctions.isExpiredUser(user))
            return -4
        // Add the itinerary to this user
        user.addToItineraries(itinerary)
        // Save
        if (!user.save()) {
            user.errors.each { println it }
            return -5
        }
        return 0
    }

    static belongsTo = [user: User]
    static hasMany = [days: Day]
    static constraints = {
        name nullable: false
        createdAt nullable: false
    }
}
