<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display-->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${createLink(controller: 'home', action: 'index')}">Itinerate</a>
        </div>

	<ul class="nav navbar-nav navbar-right">
		<li><a href="${createLink(controller: 'about', action: 'index')}">About Us</a></li>
		<li><a href="${createLink(controller: 'user', action: 'signIn')}">Sign In</a></li>
		<li><a href="${createLink(controller: 'signup', action: 'index')}">Sign Up</a></li>
		<g:if test="${session.userId}">
			<li><a href="${createLink(controller: 'profile', action: 'index')}">Itineraries</a></li>
			<li><a href="${createLink(controller: 'user', action: 'logout')}">Log Out</a></li>
		</g:if>
      </ul>
    </div>
</nav>
