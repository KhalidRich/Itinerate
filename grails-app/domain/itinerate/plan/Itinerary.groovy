package itinerate.plan

import itinerate.User
import itinerate.place.Event

import java.text.SimpleDateFormat
import java.text.DateFormat

class Itinerary
{
	String name
	String location = "Philadelphia" // no other location for now

	Date createdAt = new Date()

	List days

    public static Integer buildItinerary(String eventsParam, Long userId)
    {
        DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH)
        println eventsParam
        eventsParam = "Philadelphia Museum of Art;Fri Apr 11 2014 06:30:00 GMT-0400 (EDT);null|"
        userId = 1
        if (eventsParam == null || userId == null || userId <= 0 || eventsParam.trim().isEmpty())
            return -1
        // First, parse the itinerary
        def events = eventsParam.split("\\|")
        if (events.length < 1)
            return -2
        // Make an itinerary object
        def itinerary = new Itinerary(name: "hello")

        // Iterate over each event
        events.each {
            def eventDetails = it.split(";")
            // Details come in groups of three
            if (eventDetails.length == 3) {
                // Get the event
                def event = Event.findByName(eventDetails[0])
                println event
                // Get the start date
                def date = df.parse(eventDetails[1].substring(0,24))
                println date
            }
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
