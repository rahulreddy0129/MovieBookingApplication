package com.example.MovieBookingApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieBookingApplication.DTO.ShowDTO;
import com.example.MovieBookingApplication.Entity.Show;
import com.example.MovieBookingApplication.Service.ShowService;

@RestController
@RequestMapping("/api/show")
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	@PostMapping("/createshow")
	public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO) {
		return ResponseEntity.ok(showService.createShow(showDTO));
	}
	
	@GetMapping("/getallshows")
	public ResponseEntity<List<Show>> getAllShows() {
		return ResponseEntity.ok(showService.getAllShows());
	}
	
	@GetMapping("/getshowsbymovies/{id}")
	public ResponseEntity<List<Show>> getShowsByMovie(@RequestParam Long id) {
		return ResponseEntity.ok(showService.getShowsByMovie(id));
	}
	
	@GetMapping("/getshowsBytheater/{id}")
	public ResponseEntity<List<Show>> getShowsByTheater(@RequestParam Long id) {
		return ResponseEntity.ok(showService.getShowsByTheater(id));
	}
	
	@PutMapping("/updateshow/{id}")
	public ResponseEntity<Show> updateShow(@RequestParam Long id, @RequestBody ShowDTO showDTO) {
		return ResponseEntity.ok(showService.updateShow(id, showDTO));
	}
	
	@DeleteMapping("/deleteshow/{id}")
	public ResponseEntity<Void> deleteShow(@RequestParam Long id) {
		showService.deleteShow(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
}









