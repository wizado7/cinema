package dataaccess.entity;

import java.time.LocalDateTime;

public class TicketGroupedByOrder {
    private int orderId;
    private int ticketId;
    private int movieId;
    private String seatNumbers;
    private String movieTitle;
    private LocalDateTime screeningStartTime;
    private LocalDateTime screeningEndTime;
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setTicketId (int ticketId) {
        this.ticketId = ticketId;
    }

    public String getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public LocalDateTime getScreeningStartTime() {
        return screeningStartTime;
    }

    public void setScreeningStartTime(LocalDateTime screeningStartTime) {
        this.screeningStartTime = screeningStartTime;
    }

    public LocalDateTime getScreeningEndTime() {
        return screeningEndTime;
    }

    public void setScreeningEndTime(LocalDateTime screeningEndTime) {
        this.screeningEndTime = screeningEndTime;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
