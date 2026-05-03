<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authors</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
<div class="container">
    <nav class="top-nav">
        <a class="brand" href="<c:url value='/books' />">Library Manager</a>
        <div>
            <a href="<c:url value='/books' />">Books</a>
            <a href="<c:url value='/authors' />">Authors</a>
            <a class="button button-small" href="<c:url value='/authors/new' />">Add Author</a>
        </div>
    </nav>

    <section class="hero">
        <p class="eyebrow">Author Table</p>
        <h1>Manage Authors</h1>
        <p>Create and update author records. Books reference authors using a many-to-one relationship.</p>
    </section>

    <c:if test="${not empty successMessage}">
        <div class="alert success">${successMessage}</div>
    </c:if>

    <div class="card">
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="author" items="${authors}">
                <tr>
                    <td>${author.name}</td>
                    <td>${author.email}</td>
                    <td>${author.country}</td>
                    <td><a class="link-action" href="<c:url value='/authors/${author.id}/edit' />">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
