<!DOCTYPE html>
<html>
<header>
<g:render template="/layouts/header" />
<g:render template="/layouts/navbar" />
<script>
var itinerary = $.parseJSON("${itinerary}".replace(/&quot;/g,'"'))
</script>
</header>
<body>
<div class="panel panel-success">
  <div class="panel-heading">Your Itineraries</div>
  <div class="panel-body">
    <table class="table">
    <tr>
    <th>Title</th>
  	<th>Start Date</th>
  	<th>View Details</th> 
	</tr>
	<g:each in="${itineraries}" var="itinerary">
	<tr>
	<td>${itinerary.name}</td>
	<td>${itinerary.days[0].dayDate.format('MMM dd, yyyy')}</td>
	<td><a href=<g:createLink controller="itinerary" action="review"
              params="[itinId: "${itinerary.id}"]"/>>Details</a></td>
	<tr>
	</g:each>
	</table>
  </div>
</div>
    
</body>
</html>
