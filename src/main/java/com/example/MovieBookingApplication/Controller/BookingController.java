package com.example.MovieBookingApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieBookingApplication.DTO.BookingDTO;
import com.example.MovieBookingApplication.Entity.Booking;
import com.example.MovieBookingApplication.Entity.BookingStatus;
import com.example.MovieBookingApplication.Service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/createbooking")
	public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
		return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
	}
	
	@GetMapping("/getuserbooking/{id}")
	public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id) {
		return ResponseEntity.ok(bookingService.getUserBookings(id));
	}
	
	@GetMapping("/getshowbooking/{id}")
	public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id) {
		return ResponseEntity.ok(bookingService.getShowBookings(id));
	}
	
	@PutMapping("/{id}/confirm")
	public ResponseEntity<Booking> confirmBooking(@PathVariable Long id) {
		return ResponseEntity.ok(bookingService.confrimBooking(id));
	}
	
	@PutMapping("/{id}/cancel")
	public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
		return ResponseEntity.ok(bookingService.cancelBooking(id));
	}
	
	@GetMapping("/getbooingbystatus/{bookingStatus}")
	public ResponseEntity<List<Booking>> getBookingByStatus(@PathVariable BookingStatus bookingStatus) {
		return ResponseEntity.ok(bookingService.getBookingByStatus(bookingStatus));
	}
	
	
}





