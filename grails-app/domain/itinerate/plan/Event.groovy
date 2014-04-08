package itinerate.plan

import itinerate.place.Address

class Event
{
    String name
    List tags
    Address address
    String telephoneNumber
    String website
    Price pricing
    
    String startMonth
    String endMonth
    
    static belongsTo = [day: Day]
    static hasMany = [tags: String, hours: Hours, holidays: Holiday]
    static embedded = ['tags']
    static constraints = {
    }
}
