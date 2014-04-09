package itinerate.plan

import itinerate.place.Address
import itinerate.place.Price
import itinerate.place.Hours
import itinerate.place.Rating

class Event
{
    String name
    Address address
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
