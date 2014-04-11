package itinerate.plan
import itinerate.plan.DayOfWeek
import itinerate.place.Event

class Day
{
    DayOfWeek day
    
    static belongsTo = [itinerary: Itinerary]
    static hasMany = [events: Event]
    static constraints = {
    }
}
