<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tickets List</title>
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

        .button_view {
            text-decoration: none;
            padding: 5px 5px;
            background-color: #007BFF;
            color: white;
            border-radius: 5px;
            font-size: 14px;
            font-weight: bold;
        }

        .button_view:hover {
            background-color: #0056b3;
        }

        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination a {
            padding: 8px 12px;
            margin: 0 5px;
            text-decoration: none;
            background-color: #007BFF;
            color: white;
            border-radius: 5px;
        }

        .pagination a:hover {
            background-color: #0056b3;
        }

        form {
            margin-bottom: 20px;
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        form div {
            margin-bottom: 10px;
        }

        form label {
            font-size: 14px;
            color: #333;
            margin-bottom: 5px;
            display: block;
        }

        form input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            background-color: #fff;
            color: #333;
        }

        form button {
            background-color: #28a745;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }

        form button:hover {
            background-color: #218838;
        }

        form div input, form div select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-top: 5px;
        }

        form button {
            width: 100%;
            margin-top: 10px;
        }
        .date-inputs {
            display: flex;
            gap: 10px;
            align-items: flex-end;
        }

        .date-inputs input {
            width: calc(33.33% - 6.66px);
        }

        .date-inputs label {
            flex: 1;
            font-size: 14px;
            color: #333;
            margin-right: 10px;
            text-align: left;
        }

    </style>
</head>
<body>

<div class="container">
    <div class="header-container">
        <h1>Ticket List</h1>
        <a class="button_back" href="/">Back</a>
        <a class="button_back" href="/statistics">Statistics</a>
    </div>

    <form method="get" action="/ticketList">
        <div>
            <label for="movieTitle">Movie Title:</label>
            <input type="text" id="movieTitle" name="movieTitle" value="${param.movieTitle}">
        </div>
        <div class="date-inputs">
            <input type="number" id="day" name="day" placeholder="Day" value="${param.day}">
            <input type="number" id="month" name="month" placeholder="Month" value="${param.month}">
            <input type="number" id="year" name="year" placeholder="Year" value="${param.year}">
        </div>
        <div>
            <label for="time">Time:</label>
            <input type="time" id="time" name="time" value="${param.time}">
        </div>
        <div>
            <label for="seat">Seat Number:</label>
            <input type="text" id="seat" name="seat" value="${param.seat}">
        </div>
        <button type="submit">Filter</button>
    </form>



    <table>
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Movie Title</th>
            <th>Screening Start Time</th>
            <th>Screening End Time</th>
            <th>Seat Numbers</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ticketGroup" items="${ticketGroups}">
            <tr>
                <td>${ticketGroup.orderId}</td>
                <td>${ticketGroup.movieTitle}</td>
                <td>${ticketGroup.screeningStartTime}</td>
                <td>${ticketGroup.screeningEndTime}</td>
                <td>${ticketGroup.seatNumbers}</td>
                <td><a href="/ticketDetails?orderId=${ticketGroup.orderId}" class="button_view">View Details</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagination">

        <c:if test="${currentPage > 1}">
            <a href="?page=${currentPage - 1}&movieTitle=${movieTitle}&startTime=${startTime}&endTime=${endTime}&seat=${seat}">Previous</a>
        </c:if>


        <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="?page=${i}&movieTitle=${movieTitle}&startTime=${startTime}&endTime=${endTime}&seat=${seat}"
               class="<c:if test='${i == currentPage}'>active</c:if>">${i}</a>
        </c:forEach>


        <c:if test="${currentPage < totalPages}">
            <a href="?page=${currentPage + 1}&movieTitle=${movieTitle}&startTime=${startTime}&endTime=${endTime}&seat=${seat}">Next</a>
        </c:if>
    </div>
</div>


<a>123: ${totalPages}</a>
<a>321: ${currentPage} </a>
</body>
</html>
