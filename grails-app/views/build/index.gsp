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

    <script>
        $(document).ready(function() {
            $('#calendar').fullCalendar({
                droppable: true,
                handleWindowResize: true,
                height: 400,
                defaultView: 'agendaDay',
                allDaySlot: false,
                slotMinutes: 15,
                theme: true,
                header: {
                    left:   'title',
                    center: 'today',
                    right: 'prev,next'
                },
                titleFormat: {
                    day: 'ddd, MMM d, yy'
                },
                selectable: true,
                allDayDefault: false,
                editable: true,
                droppable: true
            });
        });
    </script>
    </head>

    <body>
        <g:render template="/layouts/navbar" />
        <h1 id="choose">Your Itinerary</h1>
        <div class="container" id="itinerary">
            <div id="calendarContainer">
                <div id="calendar"></div>
            </div>
            <div id="events">
                hello
            </div>
        </div>
    </body>
</html>
