<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #333;
            color: #fff;
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
<h1>Tickets for Order ID: ${orderId}</h1>
<table border="1">
    <thead>
    <tr>
        <th>Ticket ID</th>
        <th>Movie ID</th>
        <th>Movie Title</th>
        <th>Screening Start Time</th>
        <th>Screening End Time</th>
        <th>Seat Numbers</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ticket" items="${tickets}">
        <tr>
            <td>${ticket.ticketId}</td>
            <td>${ticket.movieId}</td>
            <td>${ticket.movieTitle}</td>
            <td>${ticket.screeningStartTime}</td>
            <td>${ticket.screeningEndTime}</td>
            <td>${ticket.seatNumbers}</td>
            <td>
                <form method="post" action="/deleteTicket">
                    <input type="hidden" name="ticketId" value="${ticket.ticketId}" />
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/ticketList">Back to Ticket List</a>
</body>
</html>
