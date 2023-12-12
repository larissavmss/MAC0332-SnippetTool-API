package br.com.usp.mac0332.snippettool.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Date;
import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.usp.mac0332.snippettool.dto.auth.AuthRegisterDto;
import br.com.usp.mac0332.snippettool.dto.user.UserUpdateDto;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer id;

	private String username;

	private String email;

	private String password;

	@CreationTimestamp
	private Date registrationDate;

	public User(AuthRegisterDto registerDto, String password) {
		this.username = registerDto.username();
		this.email = registerDto.email();
		this.password = password;
	}

	public User update(UserUpdateDto updateDto, String password) {
		if(StringUtils.isNotBlank(updateDto.username())) {
			this.username = updateDto.username();
		}
		if(StringUtils.isNotBlank(updateDto.email())) {
			this.email = updateDto.email();
		}
		if(StringUtils.isNotBlank(password)) {
			this.password = password;
		}
		return this;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
