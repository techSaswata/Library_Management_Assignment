<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
<div class="container narrow">
    <div class="card form-card">
        <h1>Something went wrong</h1>
        <div class="alert error">${errorMessage}</div>
        <a class="button" href="<c:url value='/books' />">Back to Books</a>
    </div>
</div>
</body>
</html>
