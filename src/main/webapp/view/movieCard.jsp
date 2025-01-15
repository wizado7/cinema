<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movie Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .poster img {
            width: 300px;
            height: auto;
            border-radius: 8px;
        }
        h1, h2, h3 {
            text-align: center;
            margin: 0;
        }
        .screenings {
            margin-top: 20px;
        }
        .screenings table {
            width: 100%;
            border-collapse: collapse;
        }
        .screenings th, .screenings td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        .screenings th {
            background-color: #333;
            color: #fff;
        }
        .buy-link {
            color: #007bff;
            text-decoration: none;
        }
        .buy-link:hover {
            text-decoration: underline;
        }
        .header-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;
        }
        .button_back {
            text-decoration: none;
            padding: 10px 15px;
            background-color: #007BFF;
            color: white;
            border-radius: 5px;
            font-size: 14px;
            font-weight: bold;
        }

        .button_back:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>${movie.title}</h1>
    <h2>Genre: ${movie.genre} | Duration: ${movie.duration} min | Rating: ${movie.rating}</h2>

    <div class="screenings">
        <div class="header-container">
            <h3>Available Screenings</h3>
            <a class="button_back" href="/movies">Back</a>
        </div>
        <table>
            <thead>
            <tr>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Hall</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="screening" items="${screenings}">
                <tr>
                    <td>${screening.startTime}</td>
                    <td>${screening.endTime}</td>
                    <td>${screening.hallName}</td>
                    <td><a class="buy-link" href="/buyTicket?screeningId=${screening.id}">Buy Ticket</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
