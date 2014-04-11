<!DOCTYPE html>
<html>
<header>
<g:render template="/layouts/header" />
<g:render template="/layouts/navbar" />
<script>
console.log($.parseJSON("${itinerary}".replace(/&quot;/g,'"')))
</script>
</header>
<body>
<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Panel heading</div>
  <div class="panel-body">
    <p>...</p>
  </div>
</div>
</body>
</html>