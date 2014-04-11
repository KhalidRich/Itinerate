package itinerate.place

import itinerate.plan.Day

class Event
{
    String name
    String address
    Integer zipCode // Will be depracated eventually
    String telephoneNumber
    String website
    Price pricing
    
    Integer recommendedStayTime = -1
    
    // 0 = False, 1 = True, 2 = Unknown/Unspecified
    Integer ticketsRequired
    String ticketLink
    
    List ticketExceptions
    List picturePaths
    
    static belongsTo = [day: Day]
    static hasMany = [categories: Category, operations: OperationTime, ticketExceptions: String, picturePaths: String, ratings: Rating]
    static embedded = ['ticketExceptions', 'picturePaths']
    static constraints = {
    }
}
