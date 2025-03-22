package com.example.MovieBookingApplication.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ShowDTO {

	private LocalDate showTime;
	private Double price;
	private Long movieId;
	private Long theaterid;
	
	public LocalDate getShowTime() {
		return showTime;
	}
	public void setShowTime(LocalDate showTime) {
		this.showTime = showTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Long getTheaterid() {
		return theaterid;
	}
	public void setTheaterid(Long theaterid) {
		this.theaterid = theaterid;
	}
	
}
