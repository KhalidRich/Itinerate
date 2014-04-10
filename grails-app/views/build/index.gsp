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
	
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
		
			$('#calendar').fullCalendar({
				header: {
					left: 'prev,next today',
					//center: 'title',
					right: 'agendaDay'
				},
				editable: true,
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
		
		});

	</script>
	<style>

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

	</style>
   </head>
   <body>
   	<g:render template="/layouts/navbar" />
   	<h1 id="choose">Your Itinerary</h1>
   	<div class="container" id="itinerary">
		
  		<div class="panel panel-default" id="schedule">
			<div class="panel-heading">
				<h3 class="panel-title">Schedule</h3>
		  	</div>
		  	<div class="panel-body">
		    		<div id='calendar'></div>
		  	</div>
		</div>
		<div id="searchresults">
			<div class="panel panel-default" id="search">
				<div class="panel-heading">
					<h3 class="panel-title">Search</h3>
			  	</div>
			  	<div class="panel-body">
			    		Things To Search
			  	</div>
			</div>
			<div class="panel panel-default" id="results">
				<div class="panel-heading">
					<h3 class="panel-title">Results</h3>
			  	</div>
			  	<div class="panel-body">
					<div id="draggable" class="ui-widget-content">
						<p>Met Museum</p>
					</div>
					<div id="draggable2" class="ui-widget-content">
						<p>MOMA Museum</p>
					</div>
					<div id="draggable3" class="ui-widget-content">
						<p>Fat Cat Bar</p>
					</div>
					<div style="height: 5000px; width: 1px;"></div>
			  	</div>
			</div>
		</div>
   	</div>
   </body>
</html>
