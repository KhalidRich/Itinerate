package itinerate.place

import itinerate.User

class Rating {
    Integer rating
    String title
    String text
    
    Date created = new Date()
    Date edited = new Date()
    
    static belongsTo = [event: Event, user: User]
    static constraints = {
        rating nullable: false
        text nullable: false
    }
}
