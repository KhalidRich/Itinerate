package itinerate.plan

import itinerate.User

class Itinerary
{
	String name
	String location = "Philadelphia" // no other location for now

	Date createdAt = new Date()

	List days

    static belongsTo = [user: User]
    static hasMany = [days: Day]
    static constraints = {
    	name nullable: false
    	createdAt nullable: false
    }
}
