<!DOCTYPE html>
<html>
<header>
<g:render template="/layouts/header" />
<g:render template="/layouts/navbar" />
<script>
$(document).ready(function(){
	  var seconds = 5000;
	  var step = 0;
	  var limit = 1;

	  $("#Background").addClass("image-"+step).fadeIn(500);

	  setInterval(function(){
	    $("#Background").fadeOut(1000,function(){
	       $(this).removeClass("image-"+step);
	       step = (step > limit) ? 0 : step + 1;
	      $("#Background").addClass("image-"+step).fadeIn(1000);
	    });
	  },seconds);
	});
</script>
</header>
<body>
<div id="Background">
</div>
<div id="searchbar">
<h1 id="home-title">Where to?</h1>
	<g:form role="form" id="homesearch" role="search" class="navbar-form navbar-left" >
			<div id="searchbox" class="form-group">
        		  <input type="text" class="form-control" name="cityname" placeholder="Search for your city!">
       		 </div>
        	<button id="searchbutton" type="submit" class="btn btn-default" onclick="_gaq.push(['_trackEvent', 'searchVersionBl', 'interest_form_submit', 'funnel_submit_btn']);">Search Now!</button>
	</g:form>
</div>
</body>
</html>