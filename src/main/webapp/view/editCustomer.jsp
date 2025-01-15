<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .form-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }

        button:hover {
            background-color: #0056b3;
        }

        .button_back {
            display: block;
            margin-top: 20px;
            text-align: center;
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        .button_back:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Edit Customer</h2>
    <form method="post" action="/customerUpdate">
        <input type="hidden" name="id" value="${customer.id}">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="${customer.name}" required>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" value="${customer.email}" required>

        <label for="phone">Phone</label>
        <input type="text" id="phone" name="phone" value="${customer.phone}" required>

        <button type="submit">Save Changes</button>
    </form>
    <a class="button_back" href="/customers">Back to Customers List</a>
</div>
</body>
</html>
