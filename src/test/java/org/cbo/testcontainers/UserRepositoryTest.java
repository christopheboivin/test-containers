package org.cbo.testcontainers;

import org.cbo.testcontainers.data.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void shou_save_and_retrieve_user() throws SQLException {
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

}
