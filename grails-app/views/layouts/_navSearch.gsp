<nav class="navbar navbar-default" role="navigation" id="search">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display-->
		
		<div >

		<ul class="nav navbar-nav navbar-right" id="searchNav">
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
  							<button type="submit" class="btn btn-default"  style="float: right">SEARCH</button>

								<div style="overflow: hidden; padding-right: .5em;">
								   <input type="text" class="form-control" name="keyword" placeholder="Keyword ex. Museum">
								</div>​
						</div>					
					</div>
				</g:formRemote>
				</ul>
		</div>
		<br>
			<ul class="nav navbar-nav navbar-left" id="searchCat">
            <li><a href="#" class="searchNavItem" id="attractions" onclick="<g:remoteFunction controller="itinerary" action="filter" update="events" params="[eventType:'Attraction', location:"${desiredLocation}"]" />">ATTRACTIONS <b>\\</b></a></li>
            <li><a href="#" class="searchNavItem" id="accommodations" onclick="<g:remoteFunction controller="itinerary" action="filter" update="events" params="[eventType:'Accomdation', location:"${desiredLocation}"]" />"> ACCOMODATIONS <b>\\</b></a></li>
            <li><a href="#" class="searchNavItem" id="food" onclick="<g:remoteFunction controller="itinerary" action="filter" update="events" params="[eventType:'Food', location:"${desiredLocation}"]" />"> FOOD <b>\\</b></a></li>
            <li><a href="#" class="searchNavItem" id="eventsSort" onclick="<g:remoteFunction controller="itinerary" action="filter" update="events" params="[eventType:'Events', location:"${desiredLocation}"]" />"> EVENTS </a></li>
			</ul>
    </div>
</nav>
