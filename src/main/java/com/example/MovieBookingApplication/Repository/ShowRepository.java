package com.example.MovieBookingApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MovieBookingApplication.Entity.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

}
