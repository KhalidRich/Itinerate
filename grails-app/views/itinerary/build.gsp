<!DOCTYPE html>
<html dir="ltr" lang="en-US">
  <head>
    <g:render template="/layouts/header" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/pepper-grinder/jquery-ui.css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'builder.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.print.css')}"  media='print'>
    <script src="${resource(dir: 'js', file: 'jquery-ui.custom.min.js')}"></script>
    <script src="${resource(dir: 'js', file: 'fullcalendar.min.js')}"></script>
    <script src="${resource(dir: 'js', file: 'builder.js')}"></script>
  </head>

   	<body>
		<g:render template="/layouts/navbar" />
		<div>
		<g:render template="/layouts/navSearch" />
		</div>
		<h1 id="builder-header">Your Itinerary</h1>
    <div class="container-fluid" id="itinerary">
      <div class="row" id="cal-evt-row"> 
        <div class="col-sm-6 col-lg-5">
          <div id="calendar"></div>
        </div>
        <div class="col-sm-6 col-lg-7" id="events">
          <g:each in="${searchResults}" var="event">
            <a data-toggle="modal" href="#myModal-${event.name.replaceAll(' ', '').replaceAll('\'', '')}" class="modal-link">
            <div class="panel panel-default external-event each-event" data-name="${event.name}">
                <div class="panel-body each-event-header">
                  <div class="row text-center">
                  <img src="${resource(dir:'images/event-collection/grid-pictures',file: event.picturePaths?.getAt(0))}" class="grid-image">
                </div>
              </div>
              <div class="panel-footer" id="each-event-body">
              <h4>${event.name}</h4>
              </div>
            </div>
            </a>
            <div class="modal" id="myModal-${event.name.replaceAll(' ', '').replaceAll('\'', '')}">
              <div class="modal-dialog modal-sm modal-dialog-center">
                <div class="modal-content">
                  <div class="modal-header">
                    <button class="close" data-dismiss="modal">×</button>
                    <h4>${event.name}</h4>
                  </div>
                  <div class="modal-body">
                    <div class="row text-center">
                      <img src=${resource(dir:'images/event-collection/grid-pictures',file: event.picturePaths?.getAt(0))} class="modal-image">
                    </div>
                  </div>
                  <div class="modal-footer modal-footer-left">
                    <div>Telephone Number |
                      <g:if test="${event.telephoneNumber.length() >= 10}">
                        (${event.telephoneNumber[0..2]}) ${event.telephoneNumber[3..5]}-${event.telephoneNumber[6..9]}
                      </g:if>
                      <g:else>
                        ${event.telephoneNumber}
                      </g:else>
                    </div>
                    <div>Address |
                      <g:if test="${event.address.length() >= 2}">
                        ${event.address.substring(1, event.address.length() - 1)}
                      </g:if>
                      <g:else>
                        ${event.address}
                      </g:else>
                    </div>
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
      </div><!--itinerary row -->
      <div class="row" id="button-row">
          <button type="button" class="btn btn-default btn-lg" id="saver" onclick="saveItinerary('${createLink(controller: 'build', action: 'save')}')">Save</button>
      </div>
    </div>
		
<!-- itinerary -->
  </body>
</html>
