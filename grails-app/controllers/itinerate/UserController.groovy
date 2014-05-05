package itinerate

class UserController {

    def index() { }
	
	/**
	 * Given some identification and password, returns whether or not the pair identifies a user
	 * in our database.
	 * Will return correlated error message to form
	 * If success, then should be redirected to itinerary show page
	 */
	def signIn(){
		int uid = User.verifyUser(params.username, params.password)
		switch (uid){
			case {it instanceof Integer && it>0}:
			System.out.println('in here')
			session.userId = uid
			render(contentType: 'text/json') {
				[success: true]
			}
			break
			case -1:
			render(contentType: 'text/json') {
				[success: false, message: 'Email or Password Incorrect']
			  }
			break
			case -2:
			render(contentType: 'text/json') {
				[success: false, message: 'Please Enter a Valid Username/Password ']
				
			  }
			break
			default:
			render(contentType: 'text/json') {
				[success: false, message: 'Please Enter a Valid Username/Password ']
			  }
		}
		
	}
	
	/**
	 * Log out of session
	 */
	def signOut(){
		session.userId = null
		redirect(uri:'/')
	}

	
}
