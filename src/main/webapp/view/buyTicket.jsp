<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buy Ticket</title>
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
        h1, h2 {
            text-align: center;
            margin: 0;
        }
        .seating-chart {
            display: grid;
            grid-template-columns: repeat(10, 1fr);
            grid-gap: 5px;
            margin: 20px 0;
            text-align: center;
        }
        .seat {
            width: 40px;
            height: 40px;
            background-color: lightgreen;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 4px;
            font-size: 14px;
        }
        .seat.booked {
            background-color: red;
            cursor: not-allowed;
        }
        .seat.selected {
            background-color: #007bff;
            color: white;
        }
        .seat:hover {
            background-color: #80c3f3;
        }
        .seat:active {
            background-color: #0056b3;
        }
        .total-price {
            text-align: center;
            margin-top: 20px;
        }
        .form-container {
            text-align: center;
        }
        .form-container input {
            margin: 5px;
            padding: 8px;
            width: 250px;
        }
        .form-container button {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #218838;
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
    <div class="header-container">
        <h1>Buy Ticket for Screening ${screening.id}</h1>
        <a class="button_back" href="${pageContext.request.getHeader('Referer')}">Back</a>
    </div>




    <div class="seating-chart" id="seatingChart">
        <!-- Места будут отрисовываться динамически через JavaScript НЕ ТРОГАТЬ!!! -->
    </div>


    <div class="total-price">
        <h2>Total Price: <span id="totalPrice">0</span> BTC</h2>
    </div>


    <div class="form-container">
        <form action="/buyTicket" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" required>

            <input type="hidden" id="screeningId" name="screeningId" value="${screening.id}">
            <input type="hidden" id="selectedSeats" name="selectedSeats" value="">

            <button type="submit">Buy Ticket</button>
        </form>
    </div>
</div>

<script>
    const bookedSeats = ${bookedSeats};  // ${bookedSeats}

    const rows = 10;
    const seatsPerRow = 10;

    const pricePerSeat = 10;

    const seatsContainer = document.querySelector('.seating-chart');
    const selectedSeats = new Set();
    const totalPriceElement = document.getElementById('totalPrice');
    const selectedSeatsInput = document.getElementById('selectedSeats');

    function renderSeats(row = 1, seat = 1) {
        if (row > rows) return;

        const rowDiv = document.createElement('div');
        rowDiv.classList.add('row');

        for (let i = seat; i <= seatsPerRow; i++) {
            const seatNumber = (row - 1) * seatsPerRow + i;
            const seatDiv = document.createElement('div');
            seatDiv.classList.add('seat');

            if (bookedSeats.includes(seatNumber)) {
                seatDiv.classList.add('booked');
                seatDiv.style.cursor = 'not-allowed';
                seatDiv.setAttribute('data-seat', seatNumber);
            } else {
                seatDiv.classList.remove('booked');
                seatDiv.addEventListener('click', function () {
                    if (!seatDiv.classList.contains('selected')) {
                        seatDiv.classList.add('selected');
                        selectedSeats.add(seatNumber);
                    } else {
                        seatDiv.classList.remove('selected');
                        selectedSeats.delete(seatNumber);
                    }
                    updateTotalPrice();
                });
            }
            seatDiv.textContent = seatNumber;
            rowDiv.appendChild(seatDiv);
        }

        seatsContainer.appendChild(rowDiv);

        renderSeats(row + 1, 1);
    }

    function updateTotalPrice() {
        const totalPrice = selectedSeats.size * pricePerSeat;
        totalPriceElement.textContent = totalPrice;
        selectedSeatsInput.value = Array.from(selectedSeats).join(',');
    }

    renderSeats();

</script>

</body>
</html>
