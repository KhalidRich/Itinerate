package itinerate
import itinerate.plan.DayOfWeek
import grails.converters.JSON
import itinerate.place.Address
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
							operationTimes:e.operations,
							
							]
						   }
						   )]
					}
			)
		}
		return builder.toString()
	}	
}
