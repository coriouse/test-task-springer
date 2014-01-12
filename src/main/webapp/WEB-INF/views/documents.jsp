<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>

<title>Documents list</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
</script>

<script type="text/javascript">
        function addWatermark(id,type) {
        	console.log(id+" "+type)
	        $.ajax({
	        	 url: "put/"+id+"/"+type,
	        	 type: "PUT", 
	      		 success: function(response){
	      			alert(response);
	          },
	   		     error: function(e){
	        		alert('Error: ' + e);
	
	        	}
	        });
        }
        
        function checkWatermark(id) {
        	  $.ajax({
 	        	 url: "status/"+id,
 	      		 success: function(response){
 	      			alert(response==null ? 'no' : response.toString());
 	          },
 	   		     error: function(e){
 	        		alert('Error: ' + e);
 	
 	        	}
 	        });
        	
        	
        }
        
</script>

</head>
    <body >
       <ul>
            <c:forEach items="${documents}" var="document">
                <li>
                <a href="#" onclick="addWatermark('<c:out value="${document.id}" />','<c:out value="${document.type}" />');">add watermark</a>&nbsp;
                <a href="#" onclick="checkWatermark('<c:out value="${document.id}" />');">test watermar</a>&nbsp;
                Id : <c:out value="${document.id}" />;&nbsp; Author: <c:out value="${document.author}" />;&nbsp; Title: <c:out value="${document.title}"/></li>

            </c:forEach>
        </ul>
    </body>
</html>