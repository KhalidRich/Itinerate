package itinerate

import itinerate.place.Event

class ItineraryController {
	SearchService searchService = new SearchService();
	String EMPTY_KEYWORD = "";

    def index() {
		
	}

	def build() { 
		def desiredLocation = params.cityname;
		def startDate = params.startdate;
		def endDate = params.endDate;
		def searchResults = searchService.searchByKeyword(Event.list(), EMPTY_KEYWORD);

    	if(params.iid != null) {
    		def itinerary = Itinerary.get(params.iid);
    	} else {
    		//This is being accessed from the page after landing page
    	}

    	[desiredLocation: desiredLocation, startDate: startDate, endDate: endDate, searchResults: searchResults]		
	}

	def show() {
		redirect(controller:"ShowController",action:"index")
	}

	//Should only be for POST requests	
	def search() {
		def searchResults = searchService.performSearch(params);
		def newtag = "";
		for(result in searchResults) {
			newtag += "<div class='external-event'>"
			newtag += result.name + "</div><br>"
		}
		render newtag
		[newtag: newtag]
	}
}
