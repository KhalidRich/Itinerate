package itinerate

import itinerate.place.Event

class BuildController {

    def index() {
		[events: Event.getAll()]
	}
	def Show() {
		redirect(controller:"ShowController",action:"index")
	}
    def save() {
        
    }
}
