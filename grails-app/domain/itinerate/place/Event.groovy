package itinerate.place

import itinerate.plan.Day
import itinerate.plan.Holiday

class Event
{
    String name
    String address
    String telephoneNumber
    String website
    Price pricing
    
    String startMonth
    String endMonth
    
    Boolean ticketsRequired
    String ticketLink
    
    List ticketExceptions
    List picturePaths
    
    static belongsTo = [day: Day]
    static hasMany = [tags: String, hours: Hours, holidays: Holiday, ticketExceptions: String, picturePaths: String, ratings: Rating]
    static embedded = ['tags', 'ticketExceptions', 'picturePaths']
    static constraints = {
    }
}
