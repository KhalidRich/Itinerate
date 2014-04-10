<!DOCTYPE html>
<html dir="ltr" lang="en-US">
   <head>
	 <g:render template="/layouts/header" />
	 <style>
		#draggable, #draggable2, #draggable3 { width: 100px; height: 100px; padding: 0.5em; float: left; margin: 0 10px 10px 0; }
		</style>
		<script>
		$(function() {
			$( "#draggable" ).draggable({ scroll: true });
			$( "#draggable2" ).draggable({ scroll: true, scrollSensitivity: 100 });
			$( "#draggable3" ).draggable({ scroll: true, scrollSpeed: 100 });
		});
	</script>
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.css')}">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.print.css')}"  media='print'>
	<script src="${resource(dir: 'js', file: 'jquery.min.js')}"></script>
	<script src="${resource(dir: 'js', file: 'jquery-ui.custom.min.js')}"></script>
	<script src="${resource(dir: 'js', file: 'fullcalendar.min.js')}"></script>
	<script src="${resource(dir: 'js', file: 'fullcalendar.js')}"></script>
	<script src="${resource(dir: 'js', file: 'gcal.js')}"></script>
	

	<script>
		$(document).ready(function() {
			/* initialize the external events
			-----------------------------------------------------------------*/

			$('#external-events div.external-event').each(function() {
	
				// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
				// it doesn't need to have a start or end
				var eventObject = {
					title: $.trim($(this).text()) // use the element's text as the event title
				};
		
				// store the Event Object in the DOM element so we can get to it later
				$(this).data('eventObject', eventObject);
		
				// make the event draggable using jQuery UI
				$(this).draggable({
					zIndex: 999,
					revert: true,      // will cause the event to go back to its
					revertDuration: 0  //  original position after the drag
				});
		
			});
			/* defaults */	
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
		
			$('#calendar').fullCalendar({
				//let's you drop events onto the calender
				droppable: true,
				editable: true,
				header: {
					left: 'prev,next today',
					//center: 'title',
					right: 'agendaDay'//the full day view is defaulted 
				},
				
				drop: function(date, allDay) { // this function is called when something is dropped
					
					// retrieve the dropped element's stored Event Object
					var originalEventObject = $(this).data('eventObject');
				
					// we need to copy it, so that multiple events don't have a reference to the same object
					var copiedEventObject = $.extend({}, originalEventObject);
				
					// assign it the date that was reported
					copiedEventObject.start = date;
					copiedEventObject.allDay = allDay;
				
					// render the event on the calendar
				
					$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
				
					// is the "remove after drop" checkbox checked?
					if ($('#drop-remove').is(':checked')) {
						// if so, remove the element from the "Draggable Events" list
						$(this).remove();
					}
				
				},
				/* events already on the calender at start */
				events: [
					
					{
						title: 'Long Event',
						start: new Date(y, m, d-5),
						end: new Date(y, m, d-2)
					},
					{
						id: 999,
						title: 'Repeating Event',
						start: new Date(y, m, d-3, 16, 0),
						allDay: false
					},
					{
						id: 999,
						title: 'Repeating Event',
						start: new Date(y, m, d+4, 16, 0),
						allDay: false
					},
					{
						title: 'Meeting',
						start: new Date(y, m, d, 10, 30),
						allDay: false
					},
					{
						title: 'Lunch',
						start: new Date(y, m, d, 12, 0),
						end: new Date(y, m, d, 14, 0),
						allDay: false
					},
					{
						title: 'Birthday Party',
						start: new Date(y, m, d+1, 19, 0),
						end: new Date(y, m, d+1, 22, 30),
						allDay: false
					},
					{
						title: 'Click for Google',
						start: new Date(y, m, 28),
						end: new Date(y, m, 29),
						url: 'http://google.com/'
					}
				]
			});
			//controls the height of the calender
			$('#calendar').fullCalendar('option', 'height', 700);
			
		
		});

	</script>
	<style>
		/* styles specific for the calender */
		body {
			margin-top: 40px;
			text-align: center;
			font-size: 14px;
			font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
			}

		#calendar {
			width: 200px;
			margin: 0 auto;
			}
		
		#wrap {
			width: 1100px;
			margin: 0 auto;
			}
		
		#external-events {
			float: left;
			width: 150px;
			padding: 0 10px;
			border: 1px solid #ccc;
			background: #eee;
			text-align: left;
			}
		
		#external-events h4 {
			font-size: 16px;
			margin-top: 0;
			padding-top: 1em;
			}
		
		.external-event { /* try to mimick the look of a real event */
			margin: 10px 0;
			padding: 2px 4px;
			background: #3366CC;
			color: #fff;
			font-size: .85em;
			cursor: pointer;
			}
		
		#external-events p {
			margin: 1.5em 0;
			font-size: 11px;
			color: #666;
			}
		
		#external-events p input {
			margin: 0;
			vertical-align: middle;
			}


	</style>
   </head>
   <body>
   	<g:render template="/layouts/navbar" />
   	<h1 id="choose">Your Itinerary</h1>
   	<div class="container" id="itinerary">
		<!-- This controls the schedule and calender -->
  		<div class="panel panel-default" id="schedule">
			<div class="panel-heading">
				<h3 class="panel-title">Schedule</h3>
		  	</div>
		  	<div class="panel-body">
		    		<div id='calendar'></div>
		  	</div>
		</div>
		<!-- This is for the search criteria and results -->
		<div id="searchresults">
			<div class="panel panel-default" id="search">
				<div class="panel-heading">
					<h3 class="panel-title">Search</h3>
			  	</div>
			  	<div class="panel-body">
					
					<g:form role="form" class="profileform" controller="profile" action="createuser">
					<div id="searchcontainer">
						   
						<label>Sort by:</label><br></br>
						<input type="radio" name="sort" value="price" id="price">Price
						<input type="radio" name="sort" value="reviews" id="reviews">Reviews
						<input type="radio" name="sort" value="stars" id="stars">Stars

						<div class="input-group input-group-lg" id="searchkeyword">
  							<label>Search by:</label>
							<input type="text" class="form-control" name="searchkey" placeholder="Museum">
						</div>

						<button type="submit" class="btn btn-default" id="submitbutton">Submit</button>
					</div>
					</g:form>
						 
			  	</div>
			</div>
			<div class="panel panel-default" id="results">
				<div class="panel-heading">
					<h3 class="panel-title">Results</h3>
			  	</div>
				<!-- These are the events that can be dropped onto the calendar; class external-event for script above -->
			  	<div class="panel-body">
					<div id='wrap'>
					<div id='external-events'>
						<h4>Draggable Events</h4>
						<div class='external-event'>My Event 1</div>
						<div class='external-event'>My Event 2</div>
						<div class='external-event'>My Event 3</div>
						<div class='external-event'>My Event 4</div>
						<div class='external-event'>My Event 5</div>
						<p>
						<input type='checkbox' id='drop-remove' /> <label for='drop-remove'>remove after drop</label>
						</p>
					</div>

					<div style='clear:both'></div>
					<div style="height: 5000px; width: 1px;"></div>
			  	</div>
			</div>
		</div>
   	</div>
   </body>
</html>
