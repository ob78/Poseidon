package com.nnk.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BCryptPasswordEncoder.class)
public class PasswordEncoderTests {

    @Test
    public void testPassword_whenPasswordIsCorrect() {
        // ARRANGE
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "user";

        // ACT
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("[" + hashedPassword + "]");
        // ASSERT
        assertTrue(encoder.matches(rawPassword, hashedPassword));
    }

    @Test
    public void testPassword_whenPasswordIsWrong() {
        // ARRANGE
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "CorrectPassword";

        // ACT
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("[" + hashedPassword + "]");
        // ASSERT
        assertFalse(encoder.matches("WrongPassword", hashedPassword));
    }
}
