package itinerate

import itinerate.place.Event
import itinerate.plan.Itinerary

class BuildController {

    def index() {
        [events: Event.getAll()]
    }
    def Show() {
        redirect(controller:"ShowController",action:"index")
    }
    def save() {
        def ret = Itinerary.buildItinerary(params.events, (Long) session.userId, params.itinName)
        render ret
    }
}
