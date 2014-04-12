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
<div class="panel panel-primary">
<g:each in="${itinerary.days}" var="day">
  <div class="panel-heading">${day.day}, ${day.dayDate.format('MMM dd, yyyy') }</div>
  <div class="panel-body">
    
    <table class="table">
    <tr>
    <th>Event</th>
  	<th>Photos</th>
  	<th>Venue Details</th> 
  	<th>Event Details</th>
	</tr>
	<g:each in="${day.events}" var="event">
	<tr>
	 <td>
	 <p><b>${event.name}</b></p>
	 <if: test="${event.operations[0].hours[0].startTime }" ><p>Start Time: ${event.operations[0].hours[0].startTime} </p></if:>
	 <if: test="${event.operations[0].hours[0].endTime }" ><p> End Time: ${event.operations[0].hours[0].endTime}</p></if:>
	 </td>
	 <td>
	 </td>
	 <td>
	 <p><g:if test="${event.telephoneNumber}">Telephone Number: ${event.telephoneNumber }</g:if></p>
	 <p><g:if test="${event.website}"></g:if>Website: ${event.website }</p>
	 <p><g:if test="${event.pricing}"></g:if>Prices</p>
	 <table class="table" id="smallTable">
	 <tr>
    	<td><g:if test="${event.pricing.childPrice}"></g:if><b>Child Price</b><td>
    	<td><g:if test="${event.pricing.childRange}"></g:if><b>Child Range</b><td>
    	<td><g:if test="${event.pricing.specialChildPrice}"></g:if><b>Special Child Price</b><td>
    	<td><g:if test="${event.pricing.specialChildRange}"></g:if><b>Special Child Range</b><td>
    	<td><g:if test="${event.pricing.studentPrice}"></g:if><b>Student Price</b><td>
    	<td><g:if test="${event.pricing.seniorPrice}"></g:if><b>Senior Price</b><td>
	 </tr>
	 <tr>
    	<td><g:if test="${event.pricing.childPrice}"></g:if>$${event.pricing.childPrice}<td>
    	<td><g:if test="${event.pricing.childRange}"></g:if>${event.pricing.childRange}<td>
    	<td><g:if test="${event.pricing.specialChildPrice}"></g:if>$${event.pricing.specialChildPrice}<td>
    	<td><g:if test="${event.pricing.specialChildRange}"></g:if>${event.pricing.specialChildRange}<td>
    	<td><g:if test="${event.pricing.studentPrice}"></g:if>$${event.pricing.studentPrice}<td>
    	<td><g:if test="${event.pricing.seniorPrice}"></g:if>$${event.pricing.seniorPrice}<td>
	 </tr>
	 </table>
	 </td>
	 <td>
	 <p><g:if test="${event.ticketsRequired}">Tickets Required: ${event.ticketsRequired==1}</g:if></p>
	 <p><g:if test="${event.ticketLink}"></g:if>Ticket Link: ${event.ticketLink }</p>
	 <p><g:if test="${event.ratings}">Rating: ${event.ratings[0] }</g:if></p>
	 </td>
	 <td>
	 </td>
	 <td>
	</tr>
	</g:each>
  	</table>
    
  </div>
  </g:each>
  </div>
</body>
</html>