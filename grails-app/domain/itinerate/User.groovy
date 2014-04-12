package itinerate

import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException

import itinerate.security.PasswordFunctions
import itinerate.plan.Itinerary
import itinerate.UserFunctions
import itinerate.place.Rating

class User
{
    String email = ""
    String uname
    String password
    Date loggedIn = new Date(0)
    
    UserAttributes attributes
    
    static hasMany = [itineraries: Itinerary, ratings: Rating]
    
    @Deprecated
    /**
     * Given a user name and password, creates and saves this user in the database.
     * @param uname - The to be user name of the user
     * @param password - The password of the user. Should be prevalidated and prehashed.
     * @return The long userid on success. -1 if the user already existed. -2 for violating
     *  password restrictions. -3 for all other errors
     */
    public static long createUserByUname(String username, String hashPassword)
    {
        if (username == null || hashPassword == null || username.trim().isEmpty()
            || hashPassword.trim().isEmpty())
            return -3
        
        // First, check to see if a user by this name exists
        def user = User.findByUname(username)
        if (user != null)
            return -1
        // Then, hash their password
        def pass
        try {
            pass = PasswordFunctions.createHash(hashPassword)
        } catch (InvalidKeySpecException e) {
            return -3
        } catch (NoSuchAlgorithmException e) {
            return -3
        }
        // Make sure they gave us a valid password
        if (pass == null)
            return -2
        // Create this user
        user = new User(uname: username, password: pass, attributes: new UserAttributes())
        if (!user.validate())
            return -1
        user.save()
        // Done
        user.loggedIn = new Date()
        return user.id
    }
    
    /**
     * Given an email and password, creates and saves this user in the database.
     * @param email - The email of the user
     * @param password - The password of the user. Should be prevalidated and prehashed.
     * @return The long userid on success. -1 if the user already existed. -2 for violating password restrictions. -3 for all other errors
     */
    public static long createUserByEmail(String email, String hashPassword)
    {
        if (email == null || hashPassword == null || email.trim().isEmpty() || hashPassword.trim().isEmpty())
            return -3
        
        // First, check to see if a user by this email exists
        def user = User.findByEmail(email)
        if (user != null)
            return -1
        // Then, hash their password
        def pass
        try {
            pass = PasswordFunctions.createHash(hashPassword)
        } catch (InvalidKeySpecException e) {
            return -3
        } catch (NoSuchAlgorithmException e) {
            return -3
        }
        // Make sure they gave us a valid password
        if (pass == null)
            return -2
        // Create this user
        user = new User(email: email, password: pass, attributes: new UserAttributes())
        if (!user.validate()) {
            user.errors.allErrors.each {
                println it
            }
            return -1
        }
        user.save()
        // Done
        user.loggedIn = new Date()
        return user.id
    }
    
    /**
     * Given a username, email, and password, creates and saves this user in the database.
     * @param mail - The email of the user
     * @param uname - The username of the user
     * @param password - The password of the user. Should be prevalidated and prehashed.
     * @return The long userid on success. -1 if the username or email is already used. -2 for violating password restrictions. -3 for all other errors
     */
    public static long createUserByEmailAndUname(String mail, String username, String hashPassword)
    {
        if (username == null || hashPassword == null || mail == null || username.trim().isEmpty()
            || mail.trim().isEmpty() || hashPassword.trim().isEmpty())
            return -3
        
        // First, check to see if a user by this email exists
        def user = User.findByEmail(mail)
        if (user != null)
            return -1
        // Then, make sure the uname isn't taken
        user = User.findByUname(username)
        if (user != null)
            return -1
        
        // Then, hash their password
        def pass
        try {
            pass = PasswordFunctions.createHash(hashPassword)
        } catch (InvalidKeySpecException e) {
            return -3
        } catch (NoSuchAlgorithmException e) {
            return -3
        }
        // Make sure they gave us a valid password
        if (pass == null)
            return -2
        // Create this user
        user = new User(uname: username, email: mail, password: pass, attributes: new UserAttributes())
        if (!user.validate())
            return -1
        user.save()
        // Done
        user.loggedIn = new Date()
        return user.id
    }
    
    /**
     * Given some identification and password, returns whether or not the pair identifies a user
     * in our database.
     * @param identification - The user's email or username
     * @param plainPassword - The password in short hash
     * @return The userid on success, -1 if the pair does not describe a valid user, -2 on error
     */
    public static long verifyUser(String identification, String hashPassword)
    {
        if (identification == null || hashPassword == null || identification.trim().isEmpty()
            || hashPassword.trim().isEmpty())
            return -2
        
        // Get the user object
        def user = User.findByUname(identification)
        if (user == null) {
            user = User.findByEmail(identification)
            if (user == null)
                return -1
        }
        // Get the user's actual password
        def truePass = user.password
        // Verify the user
        def valid
        try {
            valid = PasswordFunctions.verifyPassword(hashPassword, truePass)
        } catch (InvalidKeySpecException e) {
            return -2
        } catch (NoSuchAlgorithmException e) {
            return -2
        }
        if (valid) {
            user.loggedIn = new Date()
            return user.id
        }
        return -1
    }
    
    /**
     * Adds a map of attributes to the specified user.
     * @param userid - the id of the user
     * @param attributes - a map of attributes to add
     * @return 0 on success. -1 on error, -2 if the user session has expired
     */
    public static int addUserAttributes(Long userid, Map attributesToAdd)
    {
        // Checking arguments
        if (userid == null || userid <= 0 || attributesToAdd == null)
            return -1
        
        // Get the user
        def user = User.get(userid)
        if (user == null)
            return -1
        
        // Make sure they're still logged in
        if ((new Date()).getTime() - user.loggedIn.getTime() >= UserFunctions.REVALIDATION_INTERVAL)
            return -2
        
        def userAttr = UserAttributes.get(user.attributes.id)
        if (userAttr == null)
            return -1
            
        userAttr.getDomainClass().getPersistantProperties().each {
            // Retrieve the value of this attribute from the map
            def field = it.getName()
            def attr = attributesToAdd.get(field)
            // Make sure the map contained this field
            if (attr != null && it.getType().isInstance(attr))
                userAttr."$field" = attr
        }
        
        // Always validate before saving
        if (userAttr.validate())
            userAttr.save()
        else
            return -1
        return 0
    }
    
    /**
     * Given a userid, returns a Map listing all UserAttributes.
     * @param id - The userid of the user
     * @return A Map, whose keys are the names of the fields of UserAttributes
     *  and whose values are the values of those fields.
    **/
    public static Map getUserAttributes(Long id)
    {
        // Check arguments
        if (id == null || id <= 0)
            return null
        
        // Fetch and make sure the user is valid
        def user = User.get(id)
        if (user == null)
            return null
        
        // Make sure they're still logged in
        if ((new Date()).getTime() - user.loggedIn.getTime() >= UserFunctions.REVALIDATION_INTERVAL)
            return ["login" : "expired"]
        
        // Build a map of all persistent fields in the UserAttributes
        def userAttr = [:]
        def field
        user.attributes.getDomainClass().getPersistantProperties().each {
            field = it.getName()
            userAttr.put(field, user.attributes."$field")
        }
        return userAttr
    }
    
    /**
     * With great power comes great responsibility.
     * Given a userid, returns a User object for you to do whatever you want to it.
     * @param id - The userid of the User object
     * @return A User whose User.id == id, or null on error
     */
    public static User getUserFromId(Long id)
    {
        if (id == null || id <= 0)
            return null
        def user = User.get(id)
        // if (user == null)
        //    return null
        // Make sure they're still logged in
        // if ((new Date()).getTime() - user.loggedIn.getTime() >= UserFunctions.REVALIDATION_INTERVAL) {
        //    user = new User(uname: "login", password: "expired")
        //    user.discard()
        //    return user
        //}
        // user = User.get(id)
        if (user != null)
            user.loggedIn = new Date()
        return user
    }
    
    // Temporary values
    static transients = [ "loggedIn" ]
    // The index of the user is its email
    static mapping = {
        email index:true, indexAttributes: [unique:true, dropDups:true]
    }
    static constraints = {
        // The email is a valid email, which can be nothing or a proper email
        email email: true, nullable: false, blank: false
        password nullable: false
    }
}

