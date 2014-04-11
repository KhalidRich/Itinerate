package itinerate.plan

import itinerate.place.Event

class Day
{
    DayOfWeek day
    Date dayDate
    
    static belongsTo = [itinerary: Itinerary]
    static hasMany = [events: Event]
    static constraints = {
    	day nullable: false
    	dayDate nullable: false, min: new Date()
    }
}
