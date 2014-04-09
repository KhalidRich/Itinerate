<html>
<head>
<script>
$(document).ready(function(){
	$('#submitConnection').click(function(e) {
		var url = "${createLink(controller:'user',action:'signIn')}"
		var uname=$("#username").val();
		var pass=$("#password").val();
		  e.preventDefault(); // prevents normal event of button submitting form
		  $.post(url, {username: uname, password: pass}, function(data) {
		     if (!data.success) {
			     $("#username").focus();
			     $("#password").focus();
		    	 $("#add_err").html(data.message).show(); 
		     }
		     else{
		    	 window.location.href = data.uri;
			     }
		  });
		});
});
</script>
</head>		
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display-->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>"grails-app/views/home/index.gsp"
      </button>
      <a class="navbar-brand" href="${createLink(controller: 'home', action: 'index')}">Itinerate</a>
	</div>

	<ul class="nav navbar-nav navbar-right">
		<g:if test="${session.userId}">
			<li><a href="${createLink(controller: 'profile', action: 'index')}">Itineraries</a></li>
			<li><a href="${createLink(controller: 'user', action: 'logout')}">Log Out</a></li>
		</g:if>
		<g:else>
		<li><a href="${createLink(controller: 'user', action: 'signUp')}">Sign Up</a></li>
			  <li class="dropdown">
	         <a class="loginButton">
			<div class="btn-group">
			  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
			    Log In <span class="caret"></span>
			  </button>
			  <ul class="dropdown-menu" role="menu">
			  <div class="loginform-in">
				<center><h3 id="loginHeader">Sign In to Start Itinerating</h3></center>
				<center><div class="err" id="add_err"></div></center>

				<center>
				<g:form controller="user" action="login" id="form-login">
				<div class="form-group">
		           <input type="email" class="form-control" id="username" name="email" placeholder="email" size="20px">
		          </div>
		          <div class="form-group">
		            <input type="password" class="form-control" name="password" id="password" placeholder="password">
		          </div>
		          <button type="submit" class="btn btn-default" id="submitConnection">Submit</button>
				</g:form>
				</center>
				</div>
				</div>
			  </ul>
			  </div>
		   </a>
        </li>
		</g:else>
      </ul>
    </div>
    </nav>
</html>

