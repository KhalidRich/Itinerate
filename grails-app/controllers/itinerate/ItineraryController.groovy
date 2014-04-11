package itinerate
import itinerate.plan.DayOfWeek
import itinerate.place.Category
import grails.converters.JSON
import itinerate.place.Address
import itinerate.place.OperationTime
import itinerate.place.Price
import itinerate.plan.Day
import itinerate.place.Event
import itinerate.plan.Itinerary
import groovy.json.*

class ItineraryController {

    def index() { 
		
	}
	def Build() {
		
	}
	def Show() {
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
			
		OperationTime op1 = new OperationTime([startMonth:"January", endMonth:"February", ])
		OperationTime op2 = new OperationTime([startMonth:"January", endMonth:"February", ])
		
			Event event1 = new Event([name:"Play Pen",
						telephoneNumber : "2034302493024",
						website : "www.goole.com",
						ticketsRequired : true,
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
						  ticketsRequired : true,
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
					  ticketsRequired : true,
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
			Day day1 = new Day(day:DayOfWeek.MONDAY)
			.addToEvents(event1)
			.addToEvents(event2)
			.addToEvents(event3)
			Day day2 = new Day(day:DayOfWeek.TUESDAY)
			.addToEvents(event1)
			.addToEvents(event2)
			.addToEvents(event3)
			Day day3 = new Day(day:DayOfWeek.WEDNESDAY)
			.addToEvents(event1)
			.addToEvents(event2)
			.addToEvents(event3)
			Itinerary it1 = new Itinerary()
			.addToDays(day1)
			.addToDays(day2)
			.addToDays(day3)
		return [itinerary: convertToJSON(it1)]		
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
					   Day d -> [day:d.day, events: events(
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
									OperationTime o -> [startMonth:o.startMonth, endMonth:o.endMonth]
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
}
