import grails.util.Environment

class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        if (Environment.getCurrent() == Environment.PRODUCTION) {
        	"/Itinerate/$uri?" (uri: "/$uri")
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
