package com.example.MovieBookingApplication.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate showTime;
	private Double price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="movie_id", nullable = false)
	private Movie movie;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="movie_id", nullable = false)
	private Theater theater;
	
	@OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
	private List<Booking> bookings;

	public Show() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Show [id=" + id + ", showTime=" + showTime + ", price=" + price + ", movie=" + movie + ", theater="
				+ theater + ", bookings=" + bookings + "]";
	}
	
}
