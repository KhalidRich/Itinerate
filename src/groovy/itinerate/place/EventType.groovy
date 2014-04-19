package itinerate.place

enum EventType {
	ATTRACTION("Attraction"), EVENT("Event"), ACCOMMODATION("Accommodation"), FOOD("Food")

	public final String humanName

	EventType(String type)
	{
		humanName = type
	}

	public static EventType getEnumFromName(String name)
	{
		def type = null
		EventType.values().each {
			if (name.toLowerCase().equals(it.humanName.toLowerCase()))
				type = it
		}
		return type
	}
}
