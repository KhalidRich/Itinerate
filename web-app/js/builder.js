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
        droppable: true,
        drop: function(date, allDay) { // this function is called when something is dropped
            // retrieve the dropped element's stored Event Object
            var originalEventObject = $(this).data('eventObject');
            
            // we need to copy it, so that multiple events don't have a reference to the same object
            var copiedEventObject = $.extend({}, originalEventObject);
            
            // assign it the date that was reported
            copiedEventObject.start = date;
            copiedEventObject.allDay = allDay;
            
            // render the event on the calendar
            // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
            $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
            $(this).remove();
        }
    });
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

function retagEvents() {
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
}

function saveItinerary(uri) {
    var name = ""
    // do {
       // name = prompt("Save as", "");
    // } while(prompt === "");
    var events = $('#calendar').fullCalendar('clientEvents');
    var eventStrings = "";
    events.forEach(function(event) {
        eventStrings += event.title + ";" + event.start + ";" + event.end + "|"
    });
    var jqxhr = $.ajax({
        type: "POST",
        dataType: "html",
        url: uri,
        data: {
            events: eventStrings,
            itinName: name
        }
    }).done(function(ret) {
        if (String(ret) === "0")
            alert("Saved Successfully");
        else if (String(ret) === "-6")
            alert("Please sign up or sign in to save your Itinerary");
        else
            alert("Failed to Save");
    })
}

