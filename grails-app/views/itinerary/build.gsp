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
        <h1 id="choose">Your Itinerary</h1>
        <div class="container" id="itinerary">
            <div id="calendarContainer">
                <div id="calendar"></div>
            </div>
            <div id="events">
                <g:each in="${searchResults}" var="event">
                    <div class="external-event">${event.name}</div>
                </g:each>
            </div>
            <button type="button" class="btn btn-default btn-lg" id="saver" onclick="saveItinerary()">Save</button>
        </div>
		<!-- This is for the search criteria and results -->
		<div id="searchresults">
			<div class="panel panel-default" id="search">
				<div class="panel-heading">
					<h3 class="panel-title">Search</h3>
			  	</div>
			  	<div class="panel-body">
					<!-- Search form -->
					<g:formRemote name="searchForm" update="external-events" class="searchcriteria" url="[controller: 'itinerary', action: 'search']">
					<div id="searchcontainer">
						<!-- FYI, there is no default value for sort; curated events will be automatically 								defualted in the future -->
						<label>Filters:</label><br></br>
						<input type="input" name="price" placeholder="Price(USD)" id="price"><br>
						<input type="input" name="reviews" placeholder="Number of Reviews" id="reviews"><br>
						<input type="input" name="stars" placeholder="Average Rating" id="stars"><br>
						<input type="hidden" name="startDate" value="${startDate}" id="startDate" />
						<input type="hidden" name="endDate" value="${endDate}" id="endDate" />
						<input type="hidden" name="location" value="${desiredLocation}" id="location" /><br><br>
						
						<!-- searchkeyword form -->
						<div class="input-group input-group-lg" id="keyword">
  							<label>Keyword:</label>
							<input type="text" class="form-control" name="keyword" placeholder="Museum">
						</div>

						<button type="submit" class="btn btn-default" id="submitbutton">Submit</button>
					</div>
					</g:formRemote>
						 
			  	</div><!-- panel-body -->
			</div>
			<div class="panel panel-default" id="results">
				<div class="panel-heading">
					<h3 class="panel-title">Results</h3>
			  	</div>
				<!-- These are the events that can be dropped onto the calendar; 
						class external-event for script above -->
			  	<div class="panel-body">
					<div id='wrap'>
					<div id='external-events'>
						<h4>Draggable Events</h4>
						<g:each in="${searchResults}" var="event">
							<div class='external-event'>${event.name}</div>
						</g:each>
						<p>
						<!-- TODO: make this checked by default -->
						<input type='checkbox' id='drop-remove' /> <label for='drop-remove'>remove after drop</label>
						</p>
					</div>

					<div style='clear:both'></div>
					<div style="height: 5000px; width: 1px;"></div>
			  	</div>
			</div><!-- panel-default -->
		</div><!--searchresults -->
   	</div><!-- itinerary -->
   </body>
</html>
