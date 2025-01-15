<!DOCTYPE html>
<html>
<head>
    <title>Panel</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .panel-container {
            text-align: center;
        }

        .panel-container a {
            text-decoration: none;
            display: inline-block;
            padding: 15px 30px;
            margin: 10px;
            background-color: #007BFF;
            color: white;
            font-size: 16px;
            font-weight: bold;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s, transform 0.2s;
        }

        .panel-container a:hover {
            background-color: #0056b3;
            transform: translateY(-3px);
        }

        .panel-container a:active {
            transform: translateY(0);
        }
    </style>
</head>
<body>

<div class="panel-container">
    <a href="/customers">Customers</a>
    <a href="/movies">Movies</a>
    <a href="/ticketList">Tickets</a>
</div>

</body>
</html>
