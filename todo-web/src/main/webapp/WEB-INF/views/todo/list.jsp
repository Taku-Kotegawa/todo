<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Todo List</title>

<!-- Bootstrapを追加する場合削除 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">

<!-- Bootstrap CSS -->
<!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->

<style type="text/css">
.strike {
    text-decoration: line-through;
}

.inline {
    display: inline-block;
}

//.alert > ul {
//    margin-bottom: 0;
//}

//.container {
//    margin-top: 20px;
//}
</style>
</head>
<body>
    <div class="container">

        <a href="${pageContext.request.contextPath}/todo/list"><h1>Todo List</h1></a>

        <div id="todoForm">
            <t:messagesPanel />

            <form:form
                action="${pageContext.request.contextPath}/todo/create"
                method="post" modelAttribute="todoForm">
                <form:input path="todoTitle" autocomplete="off" />
                <form:errors path="todoTitle" cssClass="text-error" />
                <form:button class="btn btn-outline-primary btn-sm">Create Todo</form:button>
            </form:form>
        </div>
        <hr />
        <div id="todoList">
            <ul>
                <c:forEach items="${todos}" var="todo">
                    <li><c:choose>
                            <c:when test="${todo.finished}">
                                <span class="strike">${f:h(todo.todoTitle)}</span>
                            </c:when>
                            <c:otherwise>
                                ${f:h(todo.todoTitle)}
                                <form:form
                                    action="${pageContext.request.contextPath}/todo/finish"
                                    method="post"
                                    modelAttribute="todoForm"
                                    cssClass="inline">
                                    <form:hidden path="todoId"
                                        value="${f:h(todo.todoId)}" />
                                    <form:button class="btn btn-outline-primary btn-sm">Finish</form:button>
                                </form:form>
                            </c:otherwise>
                        </c:choose>
                        <!-- (1) -->
                        <form:form
                            action="${pageContext.request.contextPath}/todo/delete"
                            method="post" modelAttribute="todoForm"
                            cssClass="inline">
                            <!-- (2) -->
                            <form:hidden path="todoId"
                                value="${f:h(todo.todoId)}" />
                            <form:button class="btn btn-outline-danger btn-sm">Delete</form:button>
                        </form:form>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--     <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>-->
<!--     <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>-->
<!--     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>-->
</body>
</html>