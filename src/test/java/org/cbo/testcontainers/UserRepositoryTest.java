package org.cbo.testcontainers;

import org.cbo.testcontainers.data.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test-specific.properties")
@DisplayName("UserRepository awesome tests")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    static PostgreSQLContainer<?> postgres;

    @BeforeAll
    public static void setUpContainer() {
        postgres = new PostgreSQLContainer<>("postgres:12")
                .withDatabaseName("anotherdb")
                .withUsername("test2")
                .withPassword("test3");
        postgres.start();
    }

    @Test
    public void should_save_and_retrieve_user() throws SQLException {
        //given
        User testUser = new User();
        testUser.setFirstName("Phil");
        testUser.setLastName("Selway");

        userRepository.save(testUser);

        //when
        List<User> foundUsers = userRepository.findByLastName("Selway");

        //then
        assertThat(foundUsers).containsOnly(testUser);
    }

    @AfterAll
    public static void stopContainer() {
        postgres.stop();
    }

}
