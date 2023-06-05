package com.mfort.user;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationTest {

    String passwordRegex = "^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,}$";

    @Test
    public void password_success() {
        String value = "!qwerasdf12";

        boolean result = Pattern.matches(passwordRegex, value);

        assertThat(result).isTrue();
    }

    @Test
    public void password_missing_number() {
        String value = "!qwerasdf";

        boolean result = Pattern.matches(passwordRegex, value);

        assertThat(result).isFalse();
    }

    @Test
    public void password_missing_alphabet() {
        String value = "!12345678";

        boolean result = Pattern.matches(passwordRegex, value);

        assertThat(result).isFalse();
    }

    @Test
    public void password_missing_special() {
        String value = "a12345678";

        boolean result = Pattern.matches(passwordRegex, value);

        assertThat(result).isFalse();
    }

    @Test
    public void password_invalid_size() {
        String value = "!asd123";

        boolean result = Pattern.matches(passwordRegex, value);

        assertThat(result).isFalse();
    }
}
