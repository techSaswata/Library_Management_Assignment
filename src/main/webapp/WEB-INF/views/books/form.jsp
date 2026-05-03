<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${formTitle}</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
<div class="container narrow">
    <nav class="top-nav">
        <a class="brand" href="<c:url value='/books' />">Library Manager</a>
        <div>
            <a href="<c:url value='/books' />">Books</a>
            <a href="<c:url value='/authors' />">Authors</a>
        </div>
    </nav>

    <div class="card form-card">
        <h1>${formTitle}</h1>
        <p class="muted">Enter book details and assign the book to an existing author.</p>

        <form:form method="post" action="${actionUrl}" modelAttribute="book" cssClass="entity-form">
            <form:errors path="*" cssClass="alert error" element="div"/>

            <label for="title">Title</label>
            <form:input path="title" id="title"/>
            <form:errors path="title" cssClass="field-error"/>

            <label for="isbn">ISBN</label>
            <form:input path="isbn" id="isbn"/>
            <form:errors path="isbn" cssClass="field-error"/>

            <label for="genre">Genre</label>
            <form:input path="genre" id="genre"/>
            <form:errors path="genre" cssClass="field-error"/>

            <label for="publishedYear">Published Year</label>
            <form:input path="publishedYear" id="publishedYear" type="number"/>
            <form:errors path="publishedYear" cssClass="field-error"/>

            <label for="author">Author</label>
            <form:select path="author.id" id="author">
                <form:option value="" label="Select author"/>
                <form:options items="${authors}" itemValue="id" itemLabel="name"/>
            </form:select>
            <form:errors path="author" cssClass="field-error"/>

            <div class="form-actions">
                <button class="button" type="submit">Save Book</button>
                <a class="button secondary" href="<c:url value='/books' />">Cancel</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
