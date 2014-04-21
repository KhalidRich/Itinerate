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
		if(params.searchResults != null){
			[searchResults: params.searchResults]
		}
		else{
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
	}

	def show() {
		def itineraries = getAllItins()
		return [itineraries: itineraries]
	}
	
	def review() {
		// TODO: Get dynamic data derived from userId and Id number
		// For Now I am creating dummy data in order to test
		def it1 = getItin(params.itinId)
		
		return [itinerary: sortByDayTime(it1)]		
	}
	
	/*
	 * Gets the specified itinerary by id
	 */
	 def getItin(itinId){
	 	if(itinId==null){
	 		return
	 	}
	 	User currentUser = User.getUserFromId(session.userId)
	 	for(itinerary in currentUser.itineraries){
	 		System.out.print("BOOM " + itinId.toLong().getClass())
	 		if(itinerary.getId() == itinId.toLong()){
	 			System.out.print("BOOM22 " + itinId.getClass())
	 			return itinerary
	 		}

	 	}
	 	return 
	 }

	/*
	 * Gets all itineraries based on user id
	 */
	 def getAllItins(){
	 	User currentUser = User.getUserFromId(session.userId)
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


	//Should only be for POST requests	
	def search() {
		def searchResults = searchService.performSearch(params);
		def newtag = getResultsInHTML(searchResults);
		render newtag
		
	}


	/*
	* TODO: Fill in rest of this function. Up to you Ruby!
	*/
	def filter() {
		def filteredResults = searchService.filter(params);
		def newtag = getResultsInHTML(filteredResults);
		for(result in filteredResults) {
			println result.name;
		}
		render newtag
		//stuff
		render(contentType: 'text/json') {
			// currently sending to index until itinerary page is up
			[success: true, results: filteredResults]
		}
		
	}

	def getResultsInHTML(searchResults) {
		def newtag = "";
		for(event in searchResults) {
			newtag += "<a data-toggle=\"modal\" href=\"#myModal-${event.name.replaceAll(' ', '').replaceAll('\'', '')}\" class=\"modal-link\">\n" + 
			"<div class=\"panel panel-default external-event each-event\" data-name=\"${event.name}\">\n" + 
			"<div class=\"panel-body each-event-header\">\n" + 
			"<div class=\"row text-center\">\n" + 
			"<img src=\"${g.resource(dir:'images/event-collection/grid-pictures',file: event.picturePaths?.getAt(0))}\" class=\"grid-image\">\n" + 
			"</div>\n" + 
			"</div>\n" + 
			"<div class=\"panel-footer\" id=\"each-event-body\">\n" + 
			"<h4>${event.name}</h4>\n" + 
			"</div>\n" + 
			"</div>\n" + 
			"</a>\n" + 
			"<div class=\"modal\" id=\"myModal-${event.name.replaceAll(' ', '').replaceAll('\'', '')}\">\n" + 
			"<div class=\"modal-dialog modal-sm modal-dialog-center\">\n" + 
			"<div class=\"modal-content\">\n" + 
			"<div class=\"modal-header\">\n" + 
			"<button class=\"close\" data-dismiss=\"modal\">×</button>\n" + 
			"<h4>${event.name}</h4>\n" + 
			"</div>\n" + 
			"<div class=\"modal-body\">\n" + 
			"<div class=\"row text-center\">\n" + 
			"<img src=${g.resource(dir:'images/event-collection/grid-pictures',file: event.picturePaths?.getAt(0))} class=\"modal-image\">\n" + 
			"</div>\n" + 
			"</div>\n" + 
			"<div class=\"modal-footer modal-footer-left\">\n" + 
			"<div>Telephone Number |\n"
			// Set the telephone number
			if (event.telephoneNumber.length() >= 10) {
				newtag += "(${event.telephoneNumber[0..2]}) ${event.telephoneNumber[3..5]}-${event.telephoneNumber[6..9]}\n</div>\n"
			} else {
				newtag += "${event.telephoneNumber}\n</div>\n"
			}
			// Set the address
			newtag += "<div>Address |\n"
			if (event.address.length() >= 2) {
				newtag += "${event.address.substring(1, event.address.length() - 1)}\n"
			} else {
				newtag += "${event.address}\n"
			}
			// Set the adult price
			newtag += "</div>\n" + 
			"<div>Adult Price |\n"
			if (event.pricing.adultPrice == -2 || event.pricing.adultPrice == 0) {
				newtag += "Free\n"
			} else {
				newtag += "\$${event.pricing.adultPrice}\n"
			}
			// Set the child price
			newtag += "</div>\n" + 
			"<div>Child Price |\n"
			if (event.pricing.childPrice == -2 || event.pricing.childPrice == 0) {
				newtag += "Free\n"
			} else {
				newtag += "\$${event.pricing.childPrice}\n"
			}
			newtag += "</div>\n" + 
			"</div>\n" + 
			"</div>\n" + 
			"</div>\n" + 
			"</div>"
		}
		return newtag
	}
}