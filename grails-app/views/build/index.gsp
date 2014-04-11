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
                <g:each in="${events}" var="event">
                    <div class="external-event">${event.name}</div>
                </g:each>
                <script type="text/javascript">
                    $(document).ready(function() {
                        $('.external-event').each(function() {
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
                    });
                </script>
            </div>
        </div>
    </body>
</html>
