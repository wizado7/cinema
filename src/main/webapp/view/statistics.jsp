<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Statistics</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        .stat {
            font-size: 18px;
            margin: 10px 0;
        }
        .button_back {
            display: block;
            margin: 20px auto 0;
            text-align: center;
            text-decoration: none;
            padding: 10px 15px;
            background-color: #007BFF;
            color: white;
            border-radius: 5px;
            font-size: 14px;
            font-weight: bold;
            max-width: 100px;
        }

        .button_back:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Statistics</h1>
    <div class="stat">Total Tickets Sold: ${statistics.totalTickets}</div>
    <div class="stat">Total Revenue: ${statistics.totalRevenue}</div>
    <div class="stat">Most Popular Movie: ${statistics.mostPopularMovie}</div>
    <a class="button_back" href="${pageContext.request.getHeader('Referer')}">Back</a>
</div>
</body>
</html>
