<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="http://s.plantuml.com/jquery-1.11.0.min.js"></script>
<script src=http://s.plantuml.com/synchro.js>
</script>
<script>
function compress(s, change) {
  //UTF8
  s = unescape(encodeURIComponent(s));
  alert( encode64(s)  ) ;
  dest = "http://www.plantuml.com/plantuml" + "/svg/"+encode64(zip_deflate(s, 9));
  alert( dest );
  window.location.href = dest;
}

</script>


</head>
<body>



<div id=B1>
 <textarea  name="text" id="inflated" >
  @startuml
  Bo我b -> A的lice : hel我lo
  @enduml
</textarea>
</div>


<button onclick="compress($('#inflated').val(),false)">Submit</button>


</body>
</html>