package com.example.MovieBookingApplication.DTO;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {

	private String jwtToken;
	private String username;
	private Set<String> roles;
	
	
    public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Builder jwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
        return this;
    }

    public Builder username(String username) {
        this.username = username;
        return this;
    }

    public Builder roles(Set<String> roles) {
        this.roles = roles;
        return this;
    }

    public LoginResponseDTO build() {
        return new LoginResponseDTO(this);
    }
}
