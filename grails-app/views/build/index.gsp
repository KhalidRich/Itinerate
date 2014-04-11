<!DOCTYPE html>
<html dir="ltr" lang="en-US">
   <head>
    <g:render template="/layouts/header" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.print.css')}"  media='print'>
    <script src="${resource(dir: 'js', file: 'jquery.min.js')}"></script>
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
                // header: {
                //     left:   'title',
                //     center: 'today',
                //     right: 'prev,next'
                // },
                aspectRatio: 1,
                titleFormat: {
                    day: 'ddd, MMM d, yy'
                },
                selectable: true,
                allDayDefault: false,
                editable: true
            });
        
        });

    </script>
    </head>

    <body>
        <g:render template="/layouts/navbar" />
        <h1 id="choose">Your Itinerary</h1>
        <div class="container" id="itinerary">
            <div id="calendar" style="width:300px"></div>
        </div>
    </body>
</html>
