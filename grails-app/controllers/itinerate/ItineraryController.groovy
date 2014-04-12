package itinerate

class ItineraryController {

    def index() {
    	
	}
	def build() {

	}
	def show() {
		redirect(controller:"ShowController",action:"index")
	}
	
	
	/*def createitinerary() {
		def daysmap = [:]; //key is DayOfWeek, value is the day object
		def events = eventObject;  //array of event objects
		def eventsByDay= [;]; 
	
		//assuming each event has four attributes: title, startDate, endDate, price	
		//sort the events into a map, where key is startDate and value is the events
		for(event in events){
			eventsByDay[event.startDate] = event; //may be overwriting each other?
		}

		java.lang.Long userid = session.userId; //get the session user ID		
		Itinerary curItinerary = new Itinerary(); //initialize a new itinerary object
		curItinerary.belongsTo("user") = userid; //add the userid to the itinerary object
		
		// Create day obejcts based on the events 
		//itierate through eventsByDay and create a Day object for each key with all of the day's events
		for(event in eventsByDay){  
			Day curDay = new Day();
			eventmap = event.value; 
			curDay.belongTo("itinerary") = curItinerary; 	
			curDay.hasMany("events") = eventmap; 
			
			daysmap(curDay.DayOfWeek) = curDay; 
		}
	
		// Add the Day objects to the Itinerary *
		for(Day: days){ 		
			curItinerary.hasMany("days") = Day; 
		}
	}*/

	def search() {
		def sort = params.sort;
		def searchkey = params.searchkey; 
	}
}
