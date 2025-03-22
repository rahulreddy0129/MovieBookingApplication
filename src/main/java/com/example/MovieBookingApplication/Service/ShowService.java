package com.example.MovieBookingApplication.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MovieBookingApplication.DTO.ShowDTO;
import com.example.MovieBookingApplication.Entity.Booking;
import com.example.MovieBookingApplication.Entity.Movie;
import com.example.MovieBookingApplication.Entity.Show;
import com.example.MovieBookingApplication.Entity.Theater;
import com.example.MovieBookingApplication.Repository.MovieRepository;
import com.example.MovieBookingApplication.Repository.ShowRepository;
import com.example.MovieBookingApplication.Repository.TheaterRepository;

@Service
public class ShowService {

	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	
	public Show createShow(ShowDTO showDTO) {
		
		Movie movie = movieRepository.findById(showDTO.getMovieId())
						.orElseThrow(() -> new RuntimeException("No movie found with id" + showDTO.getMovieId()));
		
		Theater theater = theaterRepository.findById(showDTO.getTheaterid())
						.orElseThrow(() -> new RuntimeException("No theater found with id" + showDTO.getTheaterid()));
		
		Show show = new Show();
		show.setShowTime(showDTO.getShowTime());
		show.setPrice(showDTO.getPrice());
		show.setMovie(movie);
		show.setTheater(theater);

		return showRepository.save(show);
	}
	
	public List<Show> getAllShows() {
		return showRepository.findAll();
	}
	
	public List<Show> getShowsByMovie(Long movieid) {
		Optional<List<Show>> showsListBox = showRepository.findByMovieId(movieid);
		
		if(showsListBox.isPresent()) {
			return showsListBox.get();
		} else 
			throw new RuntimeException("No shows available for the movie"+ movieid);		
	}
	
	public List<Show> getShowsByTheater(Long theaterId) {
		Optional<List<Show>> showsListBox = showRepository.findByTheaterId(theaterId);
		
		if(showsListBox.isPresent()) {
			return showsListBox.get();
		} else 
			throw new RuntimeException("No shows available for the movie"+ theaterId);		
	}
	
	public Show updateShow(Long id, ShowDTO showDTO) {
		
		Show show = showRepository.findById(id).orElseThrow(() -> new RuntimeException("No show found with id" + id));
		
		Movie movie = movieRepository.findById(showDTO.getMovieId())
				.orElseThrow(() -> new RuntimeException("No movie found with id" + showDTO.getMovieId()));

		Theater theater = theaterRepository.findById(showDTO.getTheaterid())
						.orElseThrow(() -> new RuntimeException("No theater found with id" + showDTO.getTheaterid()));
		

		show.setShowTime(showDTO.getShowTime());
		show.setPrice(showDTO.getPrice());
		show.setMovie(movie);
		show.setTheater(theater);
		
		return showRepository.save(show);
	}
	
	public void deleteShow(Long id) {
		
		if(!showRepository.existsById(id)) {
			throw new RuntimeException("No SHow available for this id" + id);
		}
		
		List<Booking> bookings = showRepository.findById(id).get().getBookings();
		if(!bookings.isEmpty()) {
			throw new RuntimeException("Can't delete show with existing bookings");
		}
		showRepository.deleteById(id);
	}
		
	
}









