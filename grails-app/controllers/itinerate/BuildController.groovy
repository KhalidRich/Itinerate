package itinerate

class BuildController {

     def index() { 
		
	}
	def Show() {
		redirect(controller:"ShowController",action:"index")
	}
}
