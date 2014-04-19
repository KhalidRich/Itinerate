package itinerate

class SignupController {

    def index() {
    	//1. Get the JSONified version of the current (pending) user's itinerary.
    	def pendingItinerary = null/*some JSON object*/;
    	[itinerary: pendingItinerary]
    }

    def submit_form() {
    	def userId = 0
    	println(params.email)
    	println(params.username)
    	println(params.password)

    	if(params.username != null) {
    		userId = User.createUserByEmailAndUname(params.email, params.username, params.password)
		} else {
			userId = User.createUserByEmail(params.email, params.password)
		}

		if(userId > 0){
			def user = User.getUserFromId(userId)
			session.userId = userId
			redirect(controller:'itinerary', action: 'build')
			return
		}
			// notify user that this username has already been used
		else if(userId == -1){
			def errorMessage = "This user exists, try again!"
			println("This user exists, try again!");
			redirect(controller:'itinerary', action: 'build', params: [errorMessage:errorMessage])
		}
			// notify error occurred
		else{
			def errorMessage = "Please enter a valid username/password"
			println("Please enter a valid username/password");
			redirect(controller:'itinerary', action: 'build', params: [errorMessage:errorMessage])
		}
    }
}
