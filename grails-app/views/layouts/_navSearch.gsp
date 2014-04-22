<nav class="navbar navbar-default" role="navigation" id="search">
    <div class="container-fluid" id="nav-search-container">
        <!-- Brand and toggle get grouped for better mobile display-->
		
		<div class="row">
			<div class="col-md-12">
				<ul id="searchNav">
		            <g:formRemote name="searchForm" update="events" class="navbar-form navbar-right" url="[controller: 'itinerary', action: 'search']" onSuccess="retagEvents()">
							<div id="form-group">
								<!-- FYI, there is no default value for sort; curated events will be automatically 								defualted in the future -->
								<!-- > <label>Filters:</label><br></br>
								<input type="input" name="price" placeholder="Price(USD)" id="price"><br>
								<input type="input" name="reviews" placeholder="Number of Reviews" id="reviews"><br>
								<input type="input" name="stars" placeholder="Average Rating" id="stars"><br><-->
								<input type="hidden" name="startDate" value="${startDate}" id="startDate" />
								<input type="hidden" name="endDate" value="${endDate}" id="endDate" />
								<input type="hidden" name="location" value="${desiredLocation}" id="location" />
							</div>
							<!-- searchkeyword form -->
							<div class="input-group input-group-md" id="keyword">
								<input type="text" class="form-control" name="keyword" placeholder="Keyword ex. Museum" style="display: inline-block" size="20">
								<button type="submit" class="btn btn-default"  style="display: inline-block">SEARCH</button>
							</div>
					</g:formRemote>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<ul class="nav navbar-nav navbar-right" id="searchCat">
		            <li><a href="#" class="searchNavItem" id="attractions" onclick="<g:remoteFunction controller="itinerary" action="filter" update="events" params="[eventType:'Attraction', location:"${desiredLocation}"]" onSuccess="retagEvents()" />">ATTRACTIONS <b>\\</b></a></li>
		            <li><a href="#" class="searchNavItem" id="accommodations" onclick="<g:remoteFunction controller="itinerary" action="filter" update="events" params="[eventType:'Accomdation', location:"${desiredLocation}"]" onSuccess="retagEvents()" />"> ACCOMODATIONS <b>\\</b></a></li>
		            <li><a href="#" class="searchNavItem" id="food" onclick="<g:remoteFunction controller="itinerary" action="filter" update="events" params="[eventType:'Food', location:"${desiredLocation}"]" onSuccess="retagEvents()" />"> FOOD <b>\\</b></a></li>
		            <li><a href="#" class="searchNavItem" id="eventsSort" onclick="<g:remoteFunction controller="itinerary" action="filter" update="events" params="[eventType:'Events', location:"${desiredLocation}"]" onSuccess="retagEvents()" />"> EVENTS </a></li>
				</ul>
			</div>
		</div>
    </div>
</nav>
