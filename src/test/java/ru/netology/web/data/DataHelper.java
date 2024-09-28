package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    private static final Faker faker = new Faker(new Locale("en"));

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getWrongAuthInfo() {
        return new AuthInfo("vasya", "123qwerty");
    }

    private static String generateRandomLogin() {
        return faker.name().username();
    }

    private static String generateRandomPass() {
        return faker.internet().password();
    }

    public static AuthInfo generateRandomUser() {
        return new AuthInfo(generateRandomLogin(), generateRandomPass());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getRandomCode() {
        return new VerificationCode(faker.numerify("######"));
    }
}