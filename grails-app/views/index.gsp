<!DOCTYPE html>
<html>
	<head>
		<!-- Ruby's branch: Add stuff to header.gsp: Crypto.js -->
	 <link rel="stylesheet" href="${resource(dir: 'css', file: 'roomeo.css')}">
     <link rel="icon" 
      type="image/icon" 
      href="http://images2.similargroup.com/image?url=ramos-4.bloger.hr&t=2&s=10&h=12258656341838259611">
	
	</head>
	<body>
	<g:render template="/templates/navbar" />		
	<div id='container'>
	<center>
	<div class="panel panel-default" id="registerpanel">
  		<div class="panel-heading">
    	<h3 class="panel-title">Register for Itinerate Today!</h3>
  		</div>
  		<div class="panel-body">
			<g:form role="form" class="searchform" controller="signup" action="submit_form">
			  <div> <label for="username"> Username </label></div>
			  <br>
			  <div class="form-group" id="username">
			     <input type="text" class="form-control" id="username" name="username">
			  </div>

			  <div><label>Email Address</label></div>
			  <br>
			  <div class="form-group" id="schoolemail">
			    <input type="text" class="form-control" id="schoolemail" name="schoolemail" placeholder=".edu email">
			  </div>

			  <div> <label>Password </label></div>
			  <br>
			  <div class="form-group" id="password">
			    <input type="text" class="form-control" id="password" name="password">
			  </div>

			  <input id="secure_pwd" type="hidden" value="" name="securePwd"/>

			  <br>
			  <button type="submit" class="btn btn-default" id="submitbutton">Submit</button>
			</g:form>
  		</div>
  		</center>
	</div>
	<script>
		$('#password').change(function() {
			var hash = CryptoJS.SHA1($('#password').val());
			$('#secure_pwd').val(hash);
		});
	</script>
	</body>
</html>
