package itinerate.plan

class Day
{
    DayOfWeek day
    
    static belongsTo = [itinerary: Itinerary]
    static hasMany = [events: Event]
    static constraints = {
    }
}
