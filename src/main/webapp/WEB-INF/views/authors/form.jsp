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
        <p class="muted">Author name and email are unique, so duplicate values will be rejected.</p>

        <form:form method="post" action="${actionUrl}" modelAttribute="author" cssClass="entity-form">
            <form:errors path="*" cssClass="alert error" element="div"/>

            <label for="name">Name</label>
            <form:input path="name" id="name"/>
            <form:errors path="name" cssClass="field-error"/>

            <label for="email">Email</label>
            <form:input path="email" id="email" type="email"/>
            <form:errors path="email" cssClass="field-error"/>

            <label for="country">Country</label>
            <form:input path="country" id="country"/>
            <form:errors path="country" cssClass="field-error"/>

            <div class="form-actions">
                <button class="button" type="submit">Save Author</button>
                <a class="button secondary" href="<c:url value='/authors' />">Cancel</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
