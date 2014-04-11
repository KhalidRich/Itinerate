package itinerate.place

import itinerate.plan.Day
import itinerate.plan.Holiday

class Event
{
    String name
    String address
    Integer zipCode // Will be depracated eventually
    String telephoneNumber
    String website
    Price pricing
    
    String startMonth
    String endMonth
    Integer recommendedStayTime
    
    Boolean ticketsRequired
    String ticketLink
    
    List ticketExceptions
    List picturePaths
    
    static belongsTo = [day: Day]
    static hasMany = [categories: Category, hours: Hours, holidays: Holiday, ticketExceptions: String, picturePaths: String, ratings: Rating]
    static embedded = ['tags', 'ticketExceptions', 'picturePaths']
    static constraints = {
    }
}
