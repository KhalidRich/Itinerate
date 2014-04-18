package itinerate

class TravelController {

    def index() {
    	String desiredLocation = params.cityname.toString();
    	[desiredLocation: desiredLocation]

     }
}
