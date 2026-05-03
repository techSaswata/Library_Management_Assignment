<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books and Authors</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
<div class="container">
    <nav class="top-nav">
        <a class="brand" href="<c:url value='/books' />">Library Manager</a>
        <div>
            <a href="<c:url value='/books' />">Books</a>
            <a href="<c:url value='/authors' />">Authors</a>
            <a class="button button-small" href="<c:url value='/books/new' />">Add Book</a>
        </div>
    </nav>

    <section class="hero">
        <p class="eyebrow">Inner Join Result</p>
        <h1>Books with Author Details</h1>
        <p>This page uses a custom repository query with an inner join between books and authors.</p>
    </section>

    <c:if test="${not empty successMessage}">
        <div class="alert success">${successMessage}</div>
    </c:if>

    <div class="card">
        <table>
            <thead>
            <tr>
                <th>Book</th>
                <th>ISBN</th>
                <th>Genre</th>
                <th>Year</th>
                <th>Author</th>
                <th>Email</th>
                <th>Country</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${bookAuthorRows}">
                <tr>
                    <td>${row.title()}</td>
                    <td>${row.isbn()}</td>
                    <td>${row.genre()}</td>
                    <td>${row.publishedYear()}</td>
                    <td>${row.authorName()}</td>
                    <td>${row.authorEmail()}</td>
                    <td>${row.country()}</td>
                    <td><a class="link-action" href="<c:url value='/books/${row.bookId()}/edit' />">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
