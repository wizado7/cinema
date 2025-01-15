<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Customers List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin: 20px 0;
            color: #333;
        }

        a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        table {
            width: 80%;
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
            text-align: center;
        }

        td {
            font-size: 14px;
            color: #333;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        td form {
            margin: 0;
            display: inline-block;
        }

        td button {
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

        td a {
            display: inline-block;
            padding: 5px 10px;
            background-color: #3498db;
            color: white;
            border-radius: 4px;
            text-align: center;
            font-size: 12px;
            margin-right: 5px;
        }

        td a:hover {
            background-color: #2980b9;
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
    <h2>Customers List</h2>
    <a class="button_back" href="/">Back</a>
</div>
<div>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.email}</td>
            <td>${customer.phone}</td>
            <td>
                <a href="/customerUpdate?customerId=${customer.id}">Edit</a> <br> <br>
                <form method="post" action="/deleteCustomer">
                    <input type="hidden" name="customerId" value="${customer.id}" />
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <a class="button_add" href="/customersAdd">Add new customer</a>
</div>
</body>
</html>
