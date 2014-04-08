package itinerate

class UserAttributes
{
    String name
    String profilePicturePath
    Boolean verified = false
    Date lastPayed = new Date(0)
    Date previousLastPayed = new Date(-1)

    static belongsTo = [user: User]
    static constraints = {
        verified nullable: false
        lastPayed nullable: false
        previousLastPayed nullable: false
    }
}
