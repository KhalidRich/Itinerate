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
	    if (addressMap != null)
	    	addressMap = AddressStandardizer.normalizeParsedAddress(addressMap)

	    if (addressMap == null)
	    	return null
	    // Make an address and detach it
	    def address = new Address()
	    address.discard()
	    
	    // Set each component field by field
	    address.streetAddress = addressMap[AddressComponent.NUMBER] + " " + addressMap[AddressComponent.STREET]
	    address.city = addressMap[AddressComponent.CITY]
	    address.state = addressMap[AddressComponent.STATE]
	    if (addressMap[AddressComponent.ZIP] != null)
	        address.zipCode = Integer.parseInt(addressMap[AddressComponent.ZIP])
	    println addressMap
	    return address
	}
	
	static belongsTo = [event: Event]
    static constraints = {
		streetAddress blank: false
		city blank: false
		state blank: false
		country blank: false
    }
}

