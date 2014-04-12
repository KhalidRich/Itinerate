package itinerate.plan

import itinerate.place.Event
import itinerate.plan.ItineraryItem

class Day
{
    @Deprecated
    DayOfWeek day
    Date dayDate
    
    static belongsTo = [itinerary: Itinerary]
    static hasMany = [events: Event, items: ItineraryItem]
    static constraints = {
    	dayDate nullable: false
    }
}
