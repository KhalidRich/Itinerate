package itinerate

class TravelController {

    def index() { }
	
	def Home() {
		redirect(controller:"HomeController",action:"index")
	}
	
	def Itinerate() {
		redirect(controller:"ItinerateController",action:"index")
	}
}
