<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>List of images</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Header -->
<header class="bg-primary text-white text-center py-4">
    <h1>My Image list Page</h1>
</header>

<!-- Main Content -->
<div class="container mt-5">
    <form>
        <div class="mb-3">
            <label for="selectList" class="form-label">Choose an Option:</label>
            <select class="form-select" id="selectList" name="category">
                <c:forEach items="${categories}" var="item">
                    <option value="${item.name()}" selected>${item.name()}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <div class="row">
        <c:forEach items="${imageList}" var="image">
            <a href="${pageContext.request.contextPath}/image/${image.id}">
                <div class="col-md-6 mx-auto">
                    <h2 class="text-center mb-4">${image.name}</h2>
                    <img src="${image.link}" class="img-fluid">
                </div>
            </a>
        </c:forEach>
    </div>
</div>

<!-- Footer -->
<footer class="bg-dark text-white text-center py-3 mt-5">
    &copy; 2023 My images
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>