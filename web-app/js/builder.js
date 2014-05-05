var prevHeight = 0;
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
            left:   'today',
            right: 'prev,next'
        },
        titleFormat: {
            day: 'ddd, MMM d, yy'
        },
        // Set the remove function
        eventClick: function(calEvent, jsEvent, view) {
            var date = calEvent.date;
            if (typeof date == "undefined" || date == null)
                calEvent.date = new Date();
            else {
                if ((new Date()).getTime() - date.getTime() < 250) {
                    $('#calendar').fullCalendar('removeEvents', calEvent.id);
                } else
                    calEvent.date = new Date();
            }
        },
        selectable: true,
        allDayDefault: false,
        editable: true,
        droppable: true,
        eventDurationEditable: true,
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
            // $(this).remove();
        }
    });
    $('.external-event').each(function() {
        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
        // it doesn't need to have a start or end
        var eventObject = {
            title: $.trim($(this).attr("data-name")), // use the element's text as the event title
            editable: true
        };

        // store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject);

        // make the event draggable using jQuery UI
        $(this).draggable({
            zIndex: 9999,
            revert: true,      // will cause the event to go back to its
            revertDuration: 0  //  original position after the drag
        });
    });

    $('.modal-dialog').each(function() {
        $(this).css({
            'margin-left': function () {
                return -($(this).outerWidth() / 2);
            }
        });
    });
    $('.modal').on('shown.bs.modal', function() {
        $(this).find('.modal-dialog').css({
            'top': function () {
                return Math.max(0, ($(window).height() - $(this).outerHeight()) / 2);
            }
        });
    });
    calendarEventResizer();
});

var resizeTimeout;
$(window).resize(function() {
    clearTimeout(resizeTimeout);
    resizeTimeout = setTimeout(calendarEventResizer(), 70);
});

var minHeight = 250;
function calendarEventResizer() {
    if ($(window).height() != prevHeight) {
        var newHeight = $(window).height() - ($('#main-navbar').outerHeight(true) + $('#nav-search-container').outerHeight(true) + 10);
        var height = Math.max(minHeight, newHeight);
        $('#events').height(height);
        $('#calendar').fullCalendar('option', 'height', height - 10);
    }
    prevHeight = $(window).height();
}

function retagEvents() {
    $('.external-event').each(function() {
        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
        // it doesn't need to have a start or end
        var eventObject = {
            title: $.trim($(this).attr("data-name")), // use the element's text as the event title
            editable: true
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
    
    $('.modal-dialog').each(function() {
        $(this).css({
            'margin-left': function () {
                return -($(this).outerWidth() / 2);
            }
        });
    });
    $('.modal').on('shown.bs.modal', function() {
        $(this).find('.modal-dialog').css({
            'top': function () {
                return Math.max(0, ($(window).height() - $(this).outerHeight()) / 2);
            }
        });
    });
}

var name = ""
function saveItinerary(uri) {
    while(name === "") {
       name = prompt("Save as", "");
    }
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

