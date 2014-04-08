package itinerate

class TravelController {

    def index() { }
	
	def Home() {
		redirect(controller:"HomeController",action:"index")
	}
	
	def Itinerary() {
		redirect(controller:"ItineraryController",action:"index")
	}
}
