package itinerate.plan

import itinerate.User

class Itinerary
{
	String location = "Philadelphia" // no other location for now

    static belongsTo = [user: User]
    static hasMany = [days: Day]
    static constraints = {
    }
}
