<!DOCTYPE html>
<html dir="ltr" lang="en-US">
   <head>
    <g:render template="/layouts/header" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'builder.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.print.css')}"  media='print'>
    <script src="${resource(dir: 'js', file: 'jquery-ui.custom.min.js')}"></script>
    <script src="${resource(dir: 'js', file: 'fullcalendar.min.js')}"></script>
    <script src="${resource(dir: 'js', file: 'builder.js')}"></script>
  	</head>

   	<body>
       	  <g:render template="/layouts/navbar" />
          <div id="build-search">
            <g:formRemote name="searchForm" update="events" class="searchcriteria" url="[controller: 'itinerary', action: 'search']" onSuccess="retagEvents()">
		<div id="searchcontainer">
			<!-- FYI, there is no default value for sort; curated events will be automatically 								defualted in the future -->
			<!-- > <label>Filters:</label><br></br>
			<input type="input" name="price" placeholder="Price(USD)" id="price"><br>
			<input type="input" name="reviews" placeholder="Number of Reviews" id="reviews"><br>
			<input type="input" name="stars" placeholder="Average Rating" id="stars"><br><-->
			<input type="hidden" name="startDate" value="${startDate}" id="startDate" />
			<input type="hidden" name="endDate" value="${endDate}" id="endDate" />
			<input type="hidden" name="location" value="${desiredLocation}" id="location" />
	
			<!-- searchkeyword form -->
			<div class="input-group input-group-lg" id="keyword">
				<button type="submit" class="btn btn-default"  style="float: right">Submit</button>
				<div style="overflow: hidden; padding-right: .5em;">
				   <input type="text" class="form-control" name="keyword" placeholder="Keyword ex. Museum">
				</div>​
			</div>					
		</div>
	    </g:formRemote>
	</div>
        <h1 id="builder-header">Your Itinerary</h1>
        <div class="container-fluid" id="itinerary">
		<div class="row"> 
			<div class="col-sm-4 col-lg-2" id="calendarContainer">
				<div id="calendar"></div>
			</div>
			<div class="col-sm-20 col-lg-10" id="events">
			<g:each in="${searchResults}" var="event">
				<div class="panel panel-default external-event" id="each-event">
				  <div class="panel-body" id="each-event-header">
					<!-- {event.picturePath[0].resource} -->
					<div>${event.name}</div>
					<div>${event.telephoneNumber}</div>
				  </div>
				  <div class="panel-footer" id="each-event-body">
					<div id="event-price">${event.pricing.adultPrice}</div>
					<a class="btn" data-toggle="modal" href="#myModal" >Launch Modal</a>
				  </div>
				</div>
				<div class="modal" id="myModal">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">X</button>
						<!-- {event.picturePath[0].resource} -->
						<div>${event.name}</div>
					</div>
					<div class="modal-body">
						<div>${event.telephoneNumber}</div>
						<div>${event.address}</div>
						<div>${event.pricing.adultPrice}</div>
						<div>${event.pricing.childPrice}</div>
					</div>
			
				</div>
			        
			</g:each>
			</div>
		       <button type="button" class="btn btn-default btn-lg" id="saver" onclick="saveItinerary('${createLink(controller: 'build', action: 'save')}')">Save</button>
		
		</div><!--itinerary row -->
        </div>
		
<!-- itinerary -->
   </body>
</html>

