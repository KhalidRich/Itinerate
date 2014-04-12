<!DOCTYPE html>
<html>
<header>
<g:render template="/layouts/header" />
<g:render template="/layouts/navbar" />
</header>
<body>
<center>
<div class="row">
	<div class="col-md-3 col-md-offset-5">
	<div class="panel panel-default" id="registerpanel">
  		<div class="panel-heading">
    		<h3 class="panel-title">Register to Itinerate!</h3>
  		</div>
  		<div class="panel-body">
				<g:form role="form" class="searchform" controller="subscription" action="subscribe">
				  <div> <label for="username"> Username </label></div>
				  <br>
				  <div class="form-group" id="username">
				     <input type="text" class="form-control" id="username" name="username">
				  </div>
				  
				  <div> <label>Password </label></div>
				  <br>
				  <div class="form-group" id="password">
				    <input type="password" class="form-control" id="password" name="password">
				  </div>
				  
				  <div><label> Email </label></div>
				  <br>
				  <div class="form-group" id="schoolemail">
				    <input type="text" class="form-control" id="schoolemail" name="schoolemail" placeholder="ex. perkyshins@gmail.com">
				  </div>
				  <br>
				  <button type="submit" class="btn btn-default" id="submitbutton">Submit</button>
				  </g:form>
		</div>
		</div>
  	</div>
  	</div>
  	</center>
</body>

</html>