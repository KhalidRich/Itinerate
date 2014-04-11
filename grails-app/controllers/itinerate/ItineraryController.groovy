package itinerate

import itinerate.place.Address
import itinerate.place.Price
import itinerate.plan.Event

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
		Event event1 = new Event([name:"Play Pen", 
								  telephoneNumber : "2034302493024",
								  website : "www.goole.com",
								  ticketsRequired : true
								  ])
		

			
	}
}
