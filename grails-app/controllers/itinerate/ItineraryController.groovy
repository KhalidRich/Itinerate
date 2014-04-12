package itinerate
import itinerate.plan.DayOfWeek
import itinerate.place.Category
import grails.converters.JSON
import itinerate.place.Address
import itinerate.place.Hours
import itinerate.place.OperationTime
import itinerate.place.Price
import itinerate.plan.Day
import itinerate.place.Event
import itinerate.plan.Itinerary
import groovy.json.*

import itinerate.place.Event

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
		redirect(controller:"ShowController",action:"index")
	}
	
	def review() {
		// TODO: Get dynamic data derived from userId and Id number
		// For Now I am creating dummy data in order to test
		Price eprice = new Price([adultPrice:234,
			childPrice:234,
			childRange:'2-12',
			specialChildPrice:123,
			specialChildRange:'2-12',
			studentPrice:253,
			seniorPrice:333
					  ])
		Hours h1 = new Hours([startTime:1600, endTime: 1700])
		Hours h2 = new Hours([startTime:300, endTime: 500])
		OperationTime op1 = new OperationTime([startMonth:"January", endMonth:"February"])
							.addToHours(h1)
							.addToHours(h2)
		OperationTime op2 = new OperationTime([startMonth:"January", endMonth:"February"])
							.addToHours(h1)
							.addToHours(h2)
			Event event1 = new Event([name:"Play Pen",
						telephoneNumber : "2034302493024",
						website : "www.goole.com",
						ticketsRequired : 1,
						address : "17232 Money Butt Ave. Sunn,CO",
						recommendedStayTime : 6,
						zipCode:80834,
						ticketLink : "erjkewrj.com",
						pricing:eprice,
						])
						.addToCategories(Category.TOP_SITE)
						.addToCategories(Category.MUSEUM)
						.addToOperations(op1)
						.addToOperations(op2)
			Event event2 = new Event([name:"Play Pen",
						  telephoneNumber : "2034302493024",
						  website : "www.goole.com",
						  ticketsRequired : 1,
						  address : "17232 Money Butt Ave. Sunn,CO",
						  recommendedStayTime : 6,
						  zipCode:80834,
						  ticketLink : "erjkewrj.com",
						  pricing:eprice
						  ])
					    .addToCategories(Category.TOP_SITE)
						.addToCategories(Category.MUSEUM)
						.addToOperations(op1)
						.addToOperations(op2)
			Event event3 = new Event([name:"Play Pen",
					  telephoneNumber : "2034302493024",
					  website : "www.goole.com",
					  ticketsRequired : 1,
					  address : "17232 Money Butt Ave. Sunn,CO",
					  recommendedStayTime : 6,
					  zipCode:80834,
					  ticketLink : "erjkewrj.com",
					  pricing:eprice
					  ])
					.addToCategories(Category.TOP_SITE)
					.addToCategories(Category.MUSEUM)
					.addToOperations(op1)
					.addToOperations(op2)
			def today= new Date()
			Day day1 = new Day(day:DayOfWeek.MONDAY, dayDate: today + 1 )
			.addToEvents(event1)
			.addToEvents(event2)
			.addToEvents(event3)
			Day day2 = new Day(day:DayOfWeek.TUESDAY, dayDate: today + 2)
			.addToEvents(event1)
			.addToEvents(event2)
			.addToEvents(event3)
			Day day3 = new Day(day:DayOfWeek.WEDNESDAY, dayDate:today + 3 )
			.addToEvents(event1)
			.addToEvents(event2)
			.addToEvents(event3)
			Itinerary it1 = new Itinerary()
			.addToDays(day3)
			.addToDays(day1)
			.addToDays(day2)
		return [itinerary: sortByDayTime(it1)]		
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
		}
		return builder.toString()
	}	

	//Should only be for POST requests	
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
