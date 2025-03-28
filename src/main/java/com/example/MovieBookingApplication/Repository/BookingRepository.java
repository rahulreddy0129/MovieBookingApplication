package com.example.MovieBookingApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MovieBookingApplication.Entity.Booking;
import com.example.MovieBookingApplication.Entity.BookingStatus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	List<Booking> findByUserId(Long userId);
	
	List<Booking> findByShowId(Long showId);
	
	List<Booking> findByStatus(BookingStatus bookingStatus);

}
