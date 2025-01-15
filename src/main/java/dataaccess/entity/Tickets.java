package dataaccess.entity;

import dataaccess.crudoperation.annotation.Column;
import dataaccess.crudoperation.annotation.Id;
import dataaccess.crudoperation.annotation.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "tickets")
public class Tickets {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "screening_id")
    private Integer screeningId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "sale_time")
    private LocalDateTime saleTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(LocalDateTime saleTime) {
        this.saleTime = saleTime;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", screeningId=" + screeningId +
                ", orderId=" + orderId +
                ", seatNumber=" + seatNumber +
                ", price=" + price +
                ", saleTime=" + saleTime +
                '}';
    }
}
