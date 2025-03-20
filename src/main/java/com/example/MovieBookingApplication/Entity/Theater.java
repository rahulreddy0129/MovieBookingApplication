package com.example.MovieBookingApplication.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String theaterName;
	private String theaterLocation;
	private Integer theaterCapacity;
	private String theaterScreenType;
	
	public Theater() {
		super();
	}

	@OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
	private List<Show> show;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getTheaterLocation() {
		return theaterLocation;
	}

	public void setTheaterLocation(String theaterLocation) {
		this.theaterLocation = theaterLocation;
	}

	public Integer getTheaterCapacity() {
		return theaterCapacity;
	}

	public void setTheaterCapacity(Integer theaterCapacity) {
		this.theaterCapacity = theaterCapacity;
	}

	public String getTheaterScreenType() {
		return theaterScreenType;
	}

	public void setTheaterScreenType(String theaterScreenType) {
		this.theaterScreenType = theaterScreenType;
	}

	public List<Show> getShow() {
		return show;
	}

	public void setShow(List<Show> show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return "Theater [id=" + id + ", theaterName=" + theaterName + ", theaterLocation=" + theaterLocation
				+ ", theaterCapacity=" + theaterCapacity + ", theaterScreenType=" + theaterScreenType + ", show=" + show
				+ "]";
	}
	
	
}
