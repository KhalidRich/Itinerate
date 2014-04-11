package itinerate.plan

enum Holiday {
    APR_26_12("26-Apr-12"), APR_6_12("04/06/12"), APR_7_12("4/7/2012"), APR_8_12("4/8/2012"), THANKSGIVING("Thanksgiving"), CHRISTMAS_EVE("Christmas Eve"), CHRISTMAS_DAY("Christmas Day"), NEW_YEARS_EVE("New Years Eve"), NEW_YEARS_DAY("New Years Day"), MARTIN_LUTHER_KING_DAY("Martin Luther King Day"), PRESIDENTS_DAY("President\'s Day"), COLUMBUS_DAY("Columbus Day"), VETERANS_DAY("Veteran\'s Day"), MEMORIAL_DAY("Memorial Day"), LABOR_DAY("Labor Day"), EASTER("Easter"), ROSH_HASHANA_EVE("Rosh Hashana Eve"), YOM_KIPPUR_EVE("Yom Kippur Eve"), ROSH_HASHANAH("Rosh Hashanah"), YOM_KIPPUR("Yom Kippur")

    public final String humanName

    Holiday(String day)
    {
    	humanName = day.toLowerCase()
    }

    public static Holiday getHolidayFromString(String day)
    {
    	def holiday = null
    	Holiday.values().each {
    		// println "${it.humanName} | ${day.toLowerCase()} | equals: ${day.toLowerCase().equals(it.humanName)} | contains: ${day.toLowerCase().contains(it.humanName)}"
			if (day.toLowerCase().contains(it.humanName)) {
				holiday = it
				// println holiday
			}
    	}
    	return holiday
    }
}
