package com.example.MovieBookingApplication.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MovieBookingApplication.DTO.BookingDTO;
import com.example.MovieBookingApplication.Entity.Booking;
import com.example.MovieBookingApplication.Entity.BookingStatus;
import com.example.MovieBookingApplication.Entity.Show;
import com.example.MovieBookingApplication.Entity.User;
import com.example.MovieBookingApplication.Repository.BookingRepository;
import com.example.MovieBookingApplication.Repository.ShowRepository;
import com.example.MovieBookingApplication.Repository.UserRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Booking createBooking(BookingDTO bookingDTO) {
		
		Show show = showRepository.findById(bookingDTO.getShowid())
						.orElseThrow(() -> new RuntimeException("Show not found"));
		
		if(!isSeatsAvailable(show.getId(), bookingDTO.getNumberofSeats())) {
			throw new RuntimeException("Not enough seats available"); 
		}
		
		if(bookingDTO.getSeatNumbers().size() != bookingDTO.getNumberofSeats()) {
			throw new RuntimeException("seat Numbers and number of seats must be same");
		}
		
		validateDuplicateSeats(show.getId(), bookingDTO.getSeatNumbers());
		
		User user = userRepository.findById(bookingDTO.getUserId())
								.orElseThrow(() -> new RuntimeException("User not found"));
		
		Booking bookings = new Booking();
		bookings.setUser(user);
		bookings.setShow(show);
		bookings.setNumberofSeats(bookingDTO.getNumberofSeats());
		bookings.setSeatNumbers(bookingDTO.getSeatNumbers());
		bookings.setPrice(calculateTotaAmount(show.getPrice(), bookingDTO.getNumberofSeats()));
		bookings.setBookingTime(bookingDTO.getBookingTime());
		bookings.setBookingStatus(BookingStatus.PENDING);
		
		return bookingRepository.save(bookings);
	}
	
	public Boolean isSeatsAvailable(Long showId, Integer numberOfSeats) {
		
		Show show = showRepository.findById(showId)
				.orElseThrow(() -> new RuntimeException("Show not found"));
		
		int bookedSeats = show.getBookings().stream()
								.filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
								.mapToInt(Booking::getNumberofSeats)
								.sum();
		
		return (show.getTheater().getTheaterCapacity() - bookedSeats) >= numberOfSeats;
		
	}
	
	public void validateDuplicateSeats(Long showId, List<String> seatNumbers) {

		Show show = showRepository.findById(showId)
				.orElseThrow(() -> new RuntimeException("Show not found"));
		
		Set<String> occupiedSeats = show.getBookings().stream()
										.filter(b -> b.getBookingStatus() != BookingStatus.CANCELLED)
										.flatMap(b -> b.getSeatNumbers().stream())
										.collect(Collectors.toSet());
		
		List<String> duplicateSeats = seatNumbers.stream()
											.filter(occupiedSeats::contains)
											.collect(Collectors.toList());
		if(!duplicateSeats.isEmpty()) {
			throw new RuntimeException("Seats are already booked");
		}
	}
	
	public Double calculateTotaAmount(Double price, Integer numberofSeats) {
		return price * numberofSeats;
	}
	
	public List<Booking> getUserBookings(Long userId) {
		return bookingRepository.findByUserId(userId);
	}
	
	public List<Booking> getShowBookings(Long showId) {
		return bookingRepository.findByShowId(showId);
	}
	
	
	public Booking confirmBooking(Long bookingId) {
		Booking booking = bookingRepository.findById(bookingId)
										.orElseThrow(() -> new RuntimeException("Booking not found"));
		
		if(booking.getBookingStatus() != BookingStatus.PENDING) {
			throw new RuntimeException("Booking is not in pending state");
		}
		
//		Payment process started
		booking.setBookingStatus(BookingStatus.CONFIRMED);
		return bookingRepository.save(booking);
	}
	
	public Booking cancelBooking(Long bookingId) {
		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("Booking not found"));
		
		validateCancellation(booking);
		
		booking.setBookingStatus(BookingStatus.CANCELLED);
		return bookingRepository.save(booking);
	}
	
	public void validateCancellation(Booking booking) {
		LocalDateTime showTime = booking.getShow().getShowTime();
		LocalDateTime deadlinetime = showTime.minusHours(2);
		
		if(LocalDateTime.now().isAfter(deadlinetime)) {
			throw new RuntimeException("Cannot cancel Booking");
		}
		
		if(booking.getBookingStatus() == BookingStatus.CANCELLED) {
			throw new RuntimeException("Booking Already been cancelled");			
		}
	}
	
	public List<Booking> getBookingByStatus(BookingStatus bookingStatus) {
		return bookingRepository.findByStatus(bookingStatus);
	}
	
	
}









