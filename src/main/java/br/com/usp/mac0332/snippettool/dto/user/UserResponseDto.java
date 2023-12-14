package br.com.usp.mac0332.snippettool.dto.user;

import br.com.usp.mac0332.snippettool.model.User;

public record UserResponseDto(String username, String email) {

	public UserResponseDto(User user) {
		this(user.getUsername(), user.getEmail());
	}
}
