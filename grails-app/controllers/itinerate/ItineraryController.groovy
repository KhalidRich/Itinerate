package itinerate

class ItineraryController {
	SearchService searchService = new SearchService();

    def index() {
		
	}

	def build() { 
		def desiredLocation = params.cityname;
		def startDate = params.startdate;
		def endDate = params.endDate;

    	if(params.iid != null) {
    		def itinerary = Itinerary.get(params.iid);
    	} else {
    		//This is being accessed from the page after landing page
    	}

    	[desiredLocation: desiredLocation, startDate: startDate, endDate: endDate]		
	}

	def show() {
		redirect(controller:"ShowController",action:"index")
	}

	//Should only be for POST requests	
	def search() {
		def searchResults = searchService.performSearch(params);
		return results
	}
}
