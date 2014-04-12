package itinerate
import itinerate.place.Category
import itinerate.plan.DayOfWeek
import itinerate.plan.Itinerary
import itinerate.plan.Itinerary
import itinerate.place.OperationTime
import itinerate.place.Hours
import itinerate.place.Price
import itinerate.place.Event
import itinerate.plan.Day
import itinerate.plan.Itinerary
import itinerate.SearchService

class ItineraryController {
	SearchService searchService = new SearchService();
	String EMPTY_KEYWORD = "";

    def index() {
    	
	}

	def build() { 
		def desiredLocation = params.cityname;
		def startDate = params.startdate;
		def endDate = params.endDate;
		def searchResults = searchService.searchByKeyword(Event.list(), EMPTY_KEYWORD);

    	if(params.iid != null) {
    		def itinerary = Itinerary.get(params.iid);
    	} else {
    		//This is being accessed from the page after landing page
    	}

    	[desiredLocation: desiredLocation, startDate: startDate, endDate: endDate, searchResults: searchResults]		
	}

	def show() {
		def itineraries = getAllItins()
		return [itineraries: itineraries]
	}
	
	def review() {
		// TODO: Get dynamic data derived from userId and Id number
		// For Now I am creating dummy data in order to test
		def it1 = getItin(params.id)
		return [itinerary: sortByDayTime(it1)]		
	}
	
	/*
	 * Gets the specified itinerary by id
	 */
	def getItin(itinId){
		if(itinId==null){
			return
		}
		User currentUser = getUserFromId(session.userId)
		for(itinerary in currentUser.itineraries){
			if(itinerary.id == itinId)
			return itinerary
		}
		return 
	}
	
	/*
	 * Gets all itineraries based on user id
	 */
	def getAllItins(){
		User currentUser = getUserFromId(session.userId)
		return currentUser.itineraries
	}
	
	/*
	 * Sort By time, add them to an array, then sort that array by date
	 */
	def sortByDayTime(it1){
		// first sort times of all days
		for ( day in  it1.days) {
			def sortedDay = day.events.sort{it.operations[0].hours[0].startTime}
			day = sortedDay
		}
			def sortedDays = it1.days.sort{it.dayDate}
			it1.days = sortedDays
			return it1
		}
	/*def createitinerary() {
		def daysmap = [:]; //key is DayOfWeek, value is the day object
		def events = eventObject;  //array of event objects
		def eventsByDay= [;]; 
	
		//assuming each event has four attributes: title, startDate, endDate, price	
		//sort the events into a map, where key is startDate and value is the events
		for(event in events){
			eventsByDay[event.startDate] = event; //may be overwriting each other?
		}
		def sortedDays = it1.days.sort{it.dayDate}
		it1.days = sortedDays
		return it1
	}

		java.lang.Long userid = session.userId; //get the session user ID		
		Itinerary curItinerary = new Itinerary(); //initialize a new itinerary object
		curItinerary.belongsTo("user") = userid; //add the userid to the itinerary object
		
		// Create day obejcts based on the events 
		//itierate through eventsByDay and create a Day object for each key with all of the day's events
		for(event in eventsByDay){  
			Day curDay = new Day();
			eventmap = event.value; 
			curDay.belongTo("itinerary") = curItinerary; 	
			curDay.hasMany("events") = eventmap; 
			
			daysmap(curDay.DayOfWeek) = curDay; 
		}
	
	
	/*
	 * Takes in an itinerary object and returns a
	 * JSON string 
	 */
	def convertToJSON(it1){
		def builder = new groovy.json.JsonBuilder()
		def root = builder.itinerary {
				location it1.location
				name it1.name
				days(
				it1.days.collect{
					   Day d -> [day:d.day, dayDate:d.dayDate, events: events(
						   d.events.collect{
							   Event e -> [name:e.name,
							telephoneNumber : e.telephoneNumber,
							website : e.website,
							ticketsRequired : e.ticketsRequired,
							address : e.address,
							recommendedStayTime : e.recommendedStayTime,
							zipcode:e.zipCode,
							ticketLink : e.ticketLink,
							prices: [adultPrice:e.pricing.adultPrice,
							  childPrice:e.pricing.childPrice,
							  childRange:e.pricing.childRange,
							  specialChildPrice:e.pricing.specialChildPrice,
							  specialChildRange:e.pricing.specialChildRange,
							  studentPrice:e.pricing.studentPrice,
							  seniorPrice:e.pricing.seniorPrice
							],
							categories:e.categories,
							operationTimes: operationtimes(
								e.operations.collect{
									OperationTime o -> [startMonth:o.startMonth, endMonth:o.endMonth, hours:hours(
										o.hours.collect{
											Hours h -> [startTime:h.startTime, endTime: h.endTime]
										}
										)]
								}
								)
							]
						   }
						   )]
					}
			)
		// Add the Day objects to the Itinerary *
	}
	}

	//Should //only be for POST requests	
	def search() {
		def searchResults = searchService.performSearch(params);
		def newtag = "";
		for(result in searchResults) {
			newtag += "<div class='external-event'>"
			newtag += result.name + "</div><br>"
		}
		render newtag
		[newtag: newtag]
	}
}
