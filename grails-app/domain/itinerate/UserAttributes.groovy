package itinerate

class UserAttributes {
    String name
    String profilePicturePath
    Boolean verified = false
    Date lastPayed = new Date(0)

    static belongsTo = [user: User]
    static constraints = {
    }
}
