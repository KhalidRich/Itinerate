package itinerate.place

import itinerate.plan.Day

class Event
{
    String name
    String address
    String description
    Integer zipCode // Will be depracated eventually
    String telephoneNumber
    String website
    Price pricing
    Double avgRating = -1

    Integer recommendedStayTime = -1
    EventType type
    
    // 0 = False, 1 = True, 2 = Unknown/Unspecified
    Integer ticketsRequired
    String ticketLink
    
    List ticketExceptions
    List picturePaths
    List pictureSources
    
    /**
     * Given the name of the event, returns the ID uniquely identifying this event.
     * @param name - The name of this event
     * @return The event ID on success, -1 if no event existed, -2 on error
     */
    public static Long getEventFromName(String name)
    {
        if (name == null)
            return -1

        def event = Event.findByName(name)
        if (event != null)
            return event.id

        return -2
    }

    /**
     * Given an event ID and Rating, adds the given rating to this event, updating the average at the same time
     * @param rating - The Rating to add to this event
     * @param id - The ID of the event to add to
     * @return 0 if everything went fine. -1 for an argument error, -2 on all other cases
     */
    public static Integer addRating(Rating rating, Long id)
    {
        if (rating == null || id == null || id <= 0)
            return -1

        def event = Event.get(id)
        if (event == null)
            return -2

        event.addToRatings(rating)
        if (event.validate())
            event.save()

        // It's the first time we set the average
        if (event.avgRating == -1)
            event.avgRating == rating.rating
        else {
            event.avgRating *= rating.size() - 1
            event.avgRating += rating.rating
            event.avgRating /= rating.size()
        }
        return 0
    }

    static hasMany = [categories: Category, operations: OperationTime, ticketExceptions: String, picturePaths: String, pictureSources: String, ratings: Rating]
    static embedded = ['ticketExceptions', 'picturePaths', 'pictureSources']
    static constraints = {
    }
}
