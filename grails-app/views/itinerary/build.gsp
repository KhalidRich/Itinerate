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
			<div class="col-sm-6 col-lg-5">
				<div id="calendar"></div>
			</div>
			<div class="col-sm-6 col-lg-7" id="events">
				<g:each in="${searchResults}" var="event">
					<a data-toggle="modal" href="#myModal-${event.name.replaceAll(' ', '').replaceAll('\'', '')}" class="modal-link">
					<div class="panel panel-default external-event each-event" data-name="${event.name}">
					  	<div class="panel-body each-event-header">
							<img src="${resource(dir:'images/event-collection/grid-pictures',file: event.picturePaths?.getAt(0))}" class="grid-image">
					  </div>
					  <div class="panel-footer" id="each-event-body">
						<h4>${event.name}</h4>
					  </div>
					</div>
					</a>
					<div class="modal fade" id="myModal-${event.name.replaceAll(' ', '').replaceAll('\'', '')}">
						<div class="modal-dialog modal-sm modal-dialog-center">
							<div class="modal-content">
								<div class="modal-header">
									<button class="close" data-dismiss="modal">×</button>
									<h4>${event.name}</h4>
								</div>
								<div class="modal-body">
									<img src=${resource(dir:'images/event-collection/grid-pictures',file: event.picturePaths?.getAt(0))} height="150" width="150">
									<g:if test="${event.telephoneNumber.length() >= 10}">
										<div>Telephone Number | (${event.telephoneNumber[0..2]}) ${event.telephoneNumber[3..5]}-${event.telephoneNumber[6..9]}</div>
									</g:if>
									<g:else>
										<div>Telephone Number | ${event.telephoneNumber}</div>
									</g:else>
									<g:if test="${event.address.length() >= 2}">
										<div>Address | ${event.address.substring(1, event.address.length() - 1)}</div>
									</g:if>
									<g:else>
										<div>Address | ${event.address}</div>
									</g:else>
									<div>Adult Price |
										<g:if test="${event.pricing.adultPrice == -2 || event.pricing.adultPrice == 0}">
											Free
										</g:if>
										<g:else>
											$${event.pricing.adultPrice}
										</g:else>
									</div>
									<div>Child Price |
										<g:if test="${event.pricing.childPrice == -2 || event.pricing.childPrice == 0}">
											Free
										</g:if>
										<g:else>
											$${event.pricing.childPrice}
										</g:else>
									</div>
								</div>
							</div>
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
