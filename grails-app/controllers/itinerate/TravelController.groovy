package itinerate

class TravelController {

    def index() { }
	
	def Home() {
		redirect(controller:"HomeController",action:"index")
	}
	
	def Build() {
		redirect(controller:"BuildController",action:"index")
	}
}
