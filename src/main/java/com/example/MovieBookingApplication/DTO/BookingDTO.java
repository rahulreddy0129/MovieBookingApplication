package com.example.MovieBookingApplication.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.example.MovieBookingApplication.Entity.BookingStatus;

import lombok.Data;

@Data
public class BookingDTO {

	private Integer numberofSeats;
	private LocalDateTime bookingTime;
	private Double price;
	private BookingStatus bookingStatus;
	private List<String> seatNumbers;
	private Long userId;
	private Long showid;
	
	public Integer getNumberofSeats() {
		return numberofSeats;
	}
	public void setNumberofSeats(Integer numberofSeats) {
		this.numberofSeats = numberofSeats;
	}
	public LocalDateTime getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public List<String> getSeatNumbers() {
		return seatNumbers;
	}
	public void setSeatNumbers(List<String> seatNumbers) {
		this.seatNumbers = seatNumbers;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getShowid() {
		return showid;
	}
	public void setShowid(Long showid) {
		this.showid = showid;
	}
	
}
