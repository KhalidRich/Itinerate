package itinerate

class ItineraryController {

    def index() { }
	
	def Profile() { 
		redirect(controller:"ProfileController",action:"index")
	}
}
