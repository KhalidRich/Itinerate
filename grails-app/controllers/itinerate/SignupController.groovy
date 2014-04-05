package itinerate

class SignupController {

    def index() {
    	//1. Get the JSONified version of the current (pending) user's itinerary.
    	pendingItinerary = /*stuff*/;


    	//2. 

    }

    def submit_form() {
    	//1. Store user information in the database

    	//2. Redirect user to page with flash message

    }

    def registration_success() {
    	//1. Display flash message and after X seconds redirect user to home page, passing along the itinerary.
    }
}
