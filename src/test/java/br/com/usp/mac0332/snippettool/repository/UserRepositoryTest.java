package br.com.usp.mac0332.snippettool.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.usp.mac0332.snippettool.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	
	@Test
	public void findByUsername_Should_Find() {
		User user = User.builder().username("username test").email("test@email.br").password("password test").build();
		repository.save(user);
		User userFound = repository.findByUsername(user.getUsername());
		Assertions.assertThat(userFound).isNotNull();
	}
}
