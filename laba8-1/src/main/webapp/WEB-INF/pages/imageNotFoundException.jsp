<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>404</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Header -->
<header class="bg-primary text-white text-center py-4">
    <h1>Image Not found</h1>
</header>

<!-- Main Content -->
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 mx-auto">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">404!</h4>
                <p>${pageContext.exception.message}</p>
                <hr>
                <p class="mb-0">Please try again or contact support.</p>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="bg-dark text-white text-center py-3 mt-5">
    &copy; 2023 My images
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>