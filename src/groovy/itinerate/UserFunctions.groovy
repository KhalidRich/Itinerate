package itinerate

final class UserFunctions
{
    // 15 minutes, expressed in milliseconds
    public static final Long REVALIDATION_INTERVAL = 15L * 60L * 1000L;
    
    /**
     * Determines whether or not the given map is the timedout login map. Should
     * be called before using the map for other functions.
     * @param map - the Map to check
     * @return True if the map is the expired map. False otherwise
    **/
    public static boolean isExpiredMap(Map map)
    {
        // It has to be non null
        if (map == null)
            return false
        
        // It has to have exactly one pair
        if (map.size() != 1)
            return false
        
        // It's key value pair must be login and expired
        if ("expired".equals(map.login))
            return true
    }
    
    /**
     * Determines whether or not the given user is the timedout user. Should
     * be called before using a user returned from one of the User.groovy
     * functions
     * @param user - The user to check if expired
     * @return True if the user is the expired user. False otherwise
    **/
    public static boolean isExpiredUser(User user)
    {
        // The user should be non-null
        if (user != null)
            return false
        
        // The password should be expired and the username should be login
        if (user.uname.equals("login") && user.password.equals("expired"))
            return false
    }
}

