<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movies</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        h2 {
            text-align: center;
            margin: 20px 0;
            color: #333;
        }

        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #333;
            color: #fff;
            font-size: 16px;
        }

        td {
            font-size: 14px;
            color: #333;
        }

        td img {
            width: 100px;
            height: auto;
            border-radius: 4px;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .header-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin: 20px 10%;
        }

        .button_back {
            text-decoration: none;
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border-radius: 5px;
            font-size: 14px;
            font-weight: bold;
        }

        .button_back:hover {
            background-color: #0056b3;
        }

        td a {
            display: inline-block;
            margin: 5px 0;
            padding: 5px 10px;
            background-color: #3498db;
            color: white;
            border-radius: 4px;
            text-align: center;
            font-size: 12px;
            text-decoration: none;
        }

        td a:hover {
            background-color: #2980b9;
        }

        td button {
            margin: 5px 0;
            background-color: #e74c3c;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 12px;
        }

        td button:hover {
            background-color: #c0392b;
        }

        .pagination {
            text-align: center;
            margin: 20px 0;
        }

        .pagination a {
            margin: 0 5px;
            padding: 8px 12px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            font-weight: bold;
            border-radius: 5px;
        }

        .pagination a:hover {
            background-color: #0056b3;
        }

        .pagination a.active {
            background-color: #0056b3;
            pointer-events: none;
        }
        .button_add {
            display: inline-block;
            padding: 10px 20px;
            margin: 0 193px;
            background-color: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            font-weight: bold;
        }

        .button_add:hover {
            background-color: #0056b3;
        }
    </style>

</head>
<body>
<div class="header-container">
    <h2>Available Movies</h2>
    <a class="button_back" href="/">Back</a>
</div>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Duration</th>
        <th>Genre</th>
        <th>Rating</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="movie" items="${movies}">
        <tr>
            <td>${movie.id}</td>
            <td>${movie.title}</td>
            <td>${movie.duration} min</td>
            <td>${movie.genre}</td>
            <td>${movie.rating}</td>
            <td><a href="/movieCard?movieId=${movie.id}">View Details</a> <br> <a href="/moviesUpdate?movieId=${movie.id}">Edit</a> <br>
                <form method="post" action="/deleteMovie">
                <input type="hidden" name="movieId" value="${movie.id}" />
                <button type="submit">Delete</button>
            </form> </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="/movies?page=${currentPage - 1}">Previous</a>
    </c:if>

    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="/movies?page=${i}" class="<c:if test='${i == currentPage}'>active</c:if>">${i}</a>
    </c:forEach>

    <c:if test="${currentPage < totalPages}">
        <a href="/movies?page=${currentPage + 1}">Next</a>
    </c:if>
</div>
<a class="button_add" href="/movieAdd">Add new movie</a>
</body>
</html>
