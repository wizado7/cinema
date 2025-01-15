package dataaccess.entity;

import dataaccess.crudoperation.annotation.Column;
import dataaccess.crudoperation.annotation.Id;
import dataaccess.crudoperation.annotation.Table;

import java.sql.Date;
import java.time.LocalDateTime;

@Table(name = "screenings")
public class Screenings {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "hall_id")
    private Integer hallId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime  endTime;

    private String hallName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public LocalDateTime  getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime  startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime  getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime  endTime) {
        this.endTime = endTime;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    @Override
    public String toString() {
        return "Screenings{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", hallId=" + hallId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", hallName='" + hallName + '\'' +
                '}';
    }
}
