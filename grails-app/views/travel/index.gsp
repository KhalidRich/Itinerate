	

<!DOCTYPE html>
<html dir="ltr" lang="en-US">
   <head>
      <meta charset="UTF-8" />
      <title>Itinerate</title>
      <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
      <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
      <link rel="stylesheet" type="text/css" media="all" href="daterangepicker-bs3.css" />
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
      <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
      <script type="text/javascript" src="moment.js"></script>
      <script type="text/javascript" src="daterangepicker.js"></script>
   </head>
   <body>
       		<h4>Choose your dates!</h4>
            <div class="well">
			   <g:form role="form" id="traveldates" role="travel" class="navbar-form navbar-left" controller="travel" action="index">
               <form class="form-horizontal">
                 <fieldset>
                  <div class="control-group">
                    <div class="controls">
                     <div class="input-prepend input-group">
                       <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span><input type="text" style="width: 200px" name="reservation" id="reservation" class="form-control" value="03/18/2013 - 03/23/2013" />
                     </div>
                    </div>
                  </div>
                 </fieldset>
               </form>

               <script type="text/javascript">
               $(document).ready(function() {
                  $('#reservation').daterangepicker(null, function(start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                  });
               });
               </script>
            </div>
            <br></br>
            <button id="travelbutton" type="submit" class="btn btn-default">Submit!</button>
			</g:form>
			<div id="traveloptions">
				 <button id="switchbutton" type="submit" class="btn btn-default">Skip</button>
				 <button id="skipbutton" type="submit" class="btn btn-default">Switch City</button>
			</div>
   </body>