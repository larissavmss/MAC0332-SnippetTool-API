package br.com.usp.mac0332.snippettool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.usp.mac0332.snippettool.dto.auth.AuthRegisterDto;
import br.com.usp.mac0332.snippettool.dto.folder.FolderCreateDto;
import br.com.usp.mac0332.snippettool.dto.user.UserResponseDto;
import br.com.usp.mac0332.snippettool.dto.user.UserUpdateDto;
import br.com.usp.mac0332.snippettool.model.User;
import br.com.usp.mac0332.snippettool.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository repo;
	
	@Autowired
	FolderService folderService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional
	public UserResponseDto create(AuthRegisterDto registerDto) {
		User user = new User(registerDto, passwordEncoder.encode(registerDto.password()));
		FolderCreateDto folderCreateDto = new FolderCreateDto("Default");
		folderService.addFolder(folderCreateDto, user);
		UserResponseDto userResponseDto = new UserResponseDto(repo.save(user));
		return userResponseDto;
	}

	public List<UserResponseDto> findAll() {
		List<User> users = repo.findAll();
		List<UserResponseDto> usersResponseDto = users.stream().map(UserResponseDto::new).toList();
		return usersResponseDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		return new MyUserDetails(user);
	}

	public void delete(User user) {
		repo.delete(user);
	}

	public UserResponseDto update(UserUpdateDto userUpdateDto, User user) {
		user.update(userUpdateDto, passwordEncoder.encode(userUpdateDto.password()));
		UserResponseDto userResponseDto = new UserResponseDto(repo.save(user));
		return userResponseDto;
	}
}
