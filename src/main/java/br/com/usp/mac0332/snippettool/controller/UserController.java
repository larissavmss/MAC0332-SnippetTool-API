package br.com.usp.mac0332.snippettool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.usp.mac0332.snippettool.dto.user.UserResponseDto;
import br.com.usp.mac0332.snippettool.dto.user.UserUpdateDto;
import br.com.usp.mac0332.snippettool.service.MyUserDetails;
import br.com.usp.mac0332.snippettool.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAll() {
		List<UserResponseDto> response = userService.findAll();
		return ResponseEntity.ok(response);
	}

	@DeleteMapping
	public void deleteThisUser(@AuthenticationPrincipal UserDetails userDetails) {
		userService.delete(((MyUserDetails) userDetails).user);
	}

	@PutMapping
	public void editUser(@RequestBody UserUpdateDto updateDto, @AuthenticationPrincipal UserDetails userDetails) {
		userService.update(updateDto, ((MyUserDetails) userDetails).user);
	}

}
