$(document).ready(function() {
    $('#calendar').fullCalendar({
        droppable: true,
        handleWindowResize: true,
        height: 400,
        defaultView: 'agendaDay',
        allDaySlot: false,
        slotMinutes: 15,
        // theme: true,
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
});

function saveItinerary() {
    var events = $('#calendar').fullCalendar('clientEvents');
    var eventStrings = "";
    events.forEach(function(event) {
        eventStrings += event.title + ";" + event.start + ";" + event.end + "|"
    });
    var jqxhr = $.ajax({
        type: "POST",
        dataType: "html",
        url: "/Itinerate/build/save",
        data: {
            events: eventStrings
        }
    }).done(function(ret) {
        if (String(ret) === "0")
            alert("Saved Successfully");
        else
            alert("Failed to Save =(")
    })
}
