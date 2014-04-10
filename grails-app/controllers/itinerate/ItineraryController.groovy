package itinerate

class ItineraryController {

    def index() { 
		
	}
	def build() {
		
	}
	def show() {
		redirect(controller:"ShowController",action:"index")
	}
}
