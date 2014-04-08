	

<!DOCTYPE html>
<html dir="ltr" lang="en-US">
   <head>
      <meta charset="UTF-8" />
      <title>Itinerate</title>
      <link rel="icon"
      type="image/icon"
      href="http://iphonegid.ru/wp-content/uploads/2014/01/letra_i_del_arco_iris_escultura_fotografica-p153807374388565225zv7fr_400.jpg">
	<meta name="layout" content="application"/>
	<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'itinerate.css')}">
   </head>
   <body>
   		
   		<h1 id="choose">Choose Your Dates!</h1>
   		<g:form role="form" id="travelsearch" role="submit" class="navbar-form navbar-left" controller="itinerate" action="index">
   		<div id="traveldates">
  			<div class="input-group input-group-lg" id="startdate">
  				<span class="input-group-addon">Start</span>
  				<input type="text" class="form-control" name="startdate" placeholder="4/11/14">
			</div>
			<div class="input-group input-group-lg" id="enddate">
  				<span class="input-group-addon">End</span>
  				<input type="text" class="form-control" name="enddate" placeholder="4/18/14">
			</div>
            <button id="travelbutton" type="submit" class="btn btn-default">Submit!</button>
			</g:form>
			<div id="traveloptions">
				 <button id="switchbutton" type="submit" class="btn btn-default">Skip</button>
				 <button id="skipbutton" type="submit" class="btn btn-default">Switch City</button>
			</div>
   		</div>

		
   </body>
</html>
