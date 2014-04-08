package itinerate.plan

import itinerate.User

class Itinerary
{

    static belongsTo = [user: User]
    static hasMany = [days: Day]
    static constraints = {
    }
}
