package com.example.MovieBookingApplication.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.MovieBookingApplication.Entity.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

	Optional<List<Show>> findByMovieId(Long movieId);
	
	Optional<List<Show>> findByTheaterId(Long theaterId);
}
