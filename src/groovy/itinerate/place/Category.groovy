package itinerate.place

enum Category {
	TOP_SITE("Top Site"), MUSEUM("Museum"), ART_GALLERY("Art Gallery"), HISTORICAL_SITE("Historical Site"), ZOO("Zoo"), GARDEN("Garden"), PARK("Park"), NEIGHBORHOOD("Neighborhood"), FOR_KIDS("For kids"), LANDMARK("Landmark"), RELIGIOUS_SITE("Religious Site"), ART("Art"), HIKING("Hiking")

	public final String humanName

	Category(String cat)
	{
		humanName = cat
	}

	public static Category getEnumFromName(String name)
	{
		def category = null
		Category.values().each {
			if (name.toLowerCase().equals(it.humanName.toLowerCase()))
				category = it
		}
		return category
	}
}
