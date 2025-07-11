package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.PracticeFormPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BaseTest {

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1366x1085";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.headless = true;
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources  = "/successfulRegistrationTestData.csv")
    public void successfulRegistrationTest(String firstName, String lastName, String email, String genter, String phone,
                                           String day, String month, String year, String subject, String hobbies,
                                           String fileName, String address, String state, String city) {
        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGenterWrapper(genter)
                .setPhone(phone)
                .setCalendar(day, month, year)
                .setSubjectsInput(subject)
                .setHobbiesWrapper(hobbies)
                .setUploadPicture(fileName)
                .setCurrentAddress(address)
                .setStateAndSetCity(state, city)
                .buttonSubmit()
                .verifyResultModalText()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", email)
                .verifyResult("Gender", genter)
                .verifyResult("Mobile", phone)
                .verifyResult("Date of Birth", day + " " + month + "," + year)
                .verifyResult("Subjects", subject)
                .verifyResult("Hobbies", hobbies)
                .verifyResult("Picture", fileName)
                .verifyResult("Address", address);
    }

    @Test
    public void shouldHaveCityTest() {
        String[] expected = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        practiceFormPage.openPage();
        $x("//div[contains(text(), 'Select State')]").click();

        String getCity = $x("//div[contains(@class, 'css-26l3qy-menu')]").getText();
        String[] cityArray = getCity.split("\\n");

        assertArrayEquals(expected, cityArray, "Название городов не совпадает");
    }
}
