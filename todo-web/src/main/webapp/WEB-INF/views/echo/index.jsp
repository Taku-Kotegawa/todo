<!DOCTYPE html>
<html>
<head>
<title>Echo Application</title>
</head>
<body>
  <form:form modelAttribute="echoForm" action="${pageContext.request.contextPath}/echo/hello">
    <form:label path="name">Input Your Name:</form:label><br>
    <form:input path="name" autocomplete="off" /><br>
    <form:errors path="name" cssStyle="color:red" /><br>
    <input type="submit" />
  </form:form>
</body>
</html>