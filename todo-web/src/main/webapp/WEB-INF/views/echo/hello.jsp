<!DOCTYPE html>
<html>
<head>
<title>Echo Application</title>
</head>
<body>
  <p>
    Hello <c:out value="${name}" /> <%-- (2) --%>
  </p>
  <a href="${pageContext.request.contextPath}/echo">戻る</a>
</body>
</html>