package itinerate.place

enum Category {
	TOP_SITE("Top Site"), MUSEUM("Museum"), ART_GALLERY("Art Gallery"), HISTORICAL_SITE("Historical Site"), ZOO("Zoo"), GARDEN("Garden"), PARK("Park"), NEIGHBORHOOD("Neighborhood"), FOR_KIDS("For kids"), LANDMARK("Landmark"), RELIGIOUS_SITE("Religious Site")

	public final String humanName

	Category(String cat)
	{
		humanName = cat
	}

	public static Category getEnumFromName(String name)
	{
		Category.values().each {
			if (name.toLowerCase().equals(it.humanName.toLowerCase()))
				return it
		}
		return null
	}
}
