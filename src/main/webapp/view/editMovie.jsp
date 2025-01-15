<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Movie</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin: 0 0 20px;
        }
        form label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        form input, form select, form button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        form button {
            background-color: #28a745;
            color: white;
            font-weight: bold;
            border: none;
            cursor: pointer;
        }
        form button:hover {
            background-color: #218838;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 10px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Edit Movie</h2>
    <form action="/moviesUpdate" method="post">
        <input type="hidden" name="id" value="${movie.id}">
        <label for="title">Title</label>
        <input type="text" id="title" name="title" value="${movie.title}" required>

        <label for="duration">Duration (minutes)</label>
        <input type="number" id="duration" name="duration" value="${movie.duration}" required>

        <label for="genre">Genre</label>
        <input type="text" id="genre" name="genre" value="${movie.genre}" required>

        <label for="rating">Rating</label>
        <select id="rating" name="rating" required>
            <option value="G" <c:if test="${movie.rating == 'G'}">selected</c:if>>G</option>
            <option value="PG" <c:if test="${movie.rating == 'PG'}">selected</c:if>>PG</option>
            <option value="PG-13" <c:if test="${movie.rating == 'PG-13'}">selected</c:if>>PG-13</option>
            <option value="R" <c:if test="${movie.rating == 'R'}">selected</c:if>>R</option>
            <option value="NC-17" <c:if test="${movie.rating == 'NC-17'}">selected</c:if>>NC-17</option>
        </select>

        <button type="submit">Save Changes</button>
    </form>
    <a class="back-link" href="/movies">Back to Movies</a>
</div>
</body>
</html>
