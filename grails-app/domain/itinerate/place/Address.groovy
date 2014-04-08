package itinerate.place

import net.sourceforge.jgeocoder.us.AddressParser
import net.sourceforge.jgeocoder.us.AddressStandardizer
import net.sourceforge.jgeocoder.AddressComponent

import itinerate.User

class Address {
    String name
	String streetAddress
	String city
	String state
	String country
	Integer zipCode
	
	/**
	 * Returns an Address with some or all of its fields filled out from a given string
	 * @param addressString - The string containing the address. May contain newlines
	 * @return An Address representing the best effort parse results of the given string
	**/
	public static Address getAddressFromString(String addressString)
	{
	    // Parse the address into a mpa
	    def addressMap = AddressParser.parseAddress(addressString)
	    addressMap = AddressStandardizer.normalizeParsedAddress(addressMap)
	    // Make an address and detach it
	    def address = new Address()
	    
	    // Set each component field by field
	    address.streetAddress = addressMap[AddressComponent.NUMBER] + " " + addressMap[AddressComponent.STREET]
	    address.city = addressMap[AddressComponent.CITY]
	    address.state = addressMap[AddressComponent.STATE]
	    if (addressMap[AddressComponent.ZIP] != null)
	        address.zipCode = Integer.parseInt(addressMap[AddressComponent.ZIP])
	    println addressMap
	    return address
	}
	
	/**
	 * Sets a given User's address to the specified address
	 * @param userid - The id of the User whose Address is being set
	 * @param address - The Address to set the above User's address to
	 * @return Returns 0 on success. -1 if the user could not be found (or address). -2 on all other errors
	**/
	public static int setUserAddress(Long userid, Address address)
	{
	    if (userid == null || address == null || userid <= 0)
	        return -1
	    
	    def user = User.get(userid)
	    if (user == null)
	        return -1
        
        def userAddr = Address.get(user.address.id)
        if (user == null)
            return -1
        
        userAddr.getDomainClass().getPersistantProperties().each {
			// Assign each field to each other field
			def field = it.getName()
			userAddr."$field" = address."$field"
		}
		if (userAddr.validate())
		    userAddr.save()
		else
		    return -2
		return 0
	}
	
	static belongsTo = [event: Event]
    static constraints = {
		streetAddress blank: false
		city blank: false
		state blank: false
		country blank: false
    }
}

