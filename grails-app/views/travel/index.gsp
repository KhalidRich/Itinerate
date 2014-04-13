<!DOCTYPE html>
<html dir="ltr" lang="en-US">
  <head>
    <g:render template="/layouts/header" />
  </head>
  <body>
    <g:render template="/layouts/navbar" />	
    <h1 id="choose">Choose Your Dates!</h1>
    <g:form role="form" id="travelsearch" role="submit" class="navbar-form navbar-left" controller="itinerary" action="build">
      <div id="traveldates">
        <div class="input-group input-group-lg" id="startdate">
          <span class="input-group-addon">Start</span>
          <input type="text" class="form-control" name="startdate" placeholder="4/11/14">
        </div>
        <div class="input-group input-group-lg" id="enddate">
          <span class="input-group-addon">End&nbsp;</span>
          <input type="text" class="form-control" name="enddate" placeholder="4/18/14">
        </div>
        <input type="hidden" name="cityname" value="${desiredLocation}" /><br>
        <button id="travelbutton" type="submit" class="btn btn-default">Submit!</button>
      </div>
    </g:form>
    <div id="traveloptions">
      <button id="switchbutton" type="submit" class="btn btn-default">Skip</button>
      <button id="skipbutton" type="submit" class="btn btn-default">Switch City</button>
    </div>
  </body>
</html>
