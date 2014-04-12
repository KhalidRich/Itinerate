<header>
<title>Itinerate</title>
<link rel="icon"
      type="image/icon"
      href="http://iphonegid.ru/wp-content/uploads/2014/01/letra_i_del_arco_iris_escultura_fotografica-p153807374388565225zv7fr_400.jpg">
<link href="${resource(dir: 'css', file: 'bootstrap.min.css')}" rel="stylesheet">
<link rel="stylesheet" href="${resource(dir: 'style', file: 'styles.css')}">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'build.css')}">
<script src="${resource(dir: 'js', file: 'jquery.min.js')}"></script>
<script src="${resource(dir: 'js', file: 'bootstrap.min.js')}"></script>
<script type="text/javascript">
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-48085984-1', 'itinerate.net');
  ga('send', 'pageview');

</script>
<script type="text/javascript">
 $(document).ready(function(){
    $('#submitConnection').click(function(e) {
        var url = "${createLink(controller:'user',action:'signIn')}"
        var uname=$("#username").val();
        var pass=$("#password").val();
          e.preventDefault(); // prevents normal event of button submitting form
          $.post(url, {username: uname, password: pass}, function(data) {
             if (!data.success) {
                 $("#username").focus();
                 $("#password").focus();
                 $("#add_err").html(data.message).show(); 
             }
             else{
                 window.location.href = "${createLink(controller:'build',action:'index')}";
                 }
          });
        });
 });
</script>
</header>
