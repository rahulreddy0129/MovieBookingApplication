package com.example.MovieBookingApplication.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MovieBookingApplication.DTO.MovieDTO;
import com.example.MovieBookingApplication.Entity.Movie;
import com.example.MovieBookingApplication.Repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;

	public Movie addMovie(MovieDTO movieDTO) {
		
		Movie movie = new Movie();
		movie.setName(movieDTO.getName());
		movie.setDescription(movieDTO.getDescription());
		movie.setDuration(movieDTO.getDuration());
		movie.setGenre(movieDTO.getGenre());
		movie.setLanguage(movieDTO.getLanguage());
		movie.setReleaseDate(movieDTO.getReleaseDate());

		return movieRepository.save(movie);
	}
	
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	
	public List<Movie> getMoviesByGenre(String genre) {
		Optional<List<Movie>> listOfMovieBox =  movieRepository.findByGenre(genre);
		
		if(listOfMovieBox.isPresent()) {
			return listOfMovieBox.get();
		} else {
			throw new RuntimeException("No Moives found in this genre" + genre);
		}
	}
	
	public List<Movie> getMoviesByLanguage(String language) {
		Optional<List<Movie>> listOfMovieBox =  movieRepository.findByLanguage(language);
		
		if(listOfMovieBox.isPresent()) {
			return listOfMovieBox.get();
		} else {
			throw new RuntimeException("No Moives found in this language" + language);
		}
	}
	
	public Movie getMovieBytitle(String title) {
		Optional<Movie> movieBox = movieRepository.findByName(title);
		if(movieBox.isPresent()) {
			return movieBox.get();
		} else
			throw new RuntimeException("No movie found by this name"+ title);
	}
	
	public Movie updateMovie(Long id, MovieDTO movieDTO) {
		
		Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("No movie found by id" + id));
		
		movie.setName(movieDTO.getName());
		movie.setDescription(movieDTO.getDescription());
		movie.setDuration(movieDTO.getDuration());
		movie.setGenre(movieDTO.getGenre());
		movie.setLanguage(movieDTO.getLanguage());
		movie.setReleaseDate(movieDTO.getReleaseDate());
		
		return movieRepository.save(movie);
	}
	
	public void deleteMovie(Long id) {
		movieRepository.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
}


















