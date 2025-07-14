package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.demoqa.helper.GenerateData;
import com.demoqa.pages.PracticeFormPage;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BaseTest {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    GenerateData generateData = new GenerateData();
    Faker faker = new Faker();

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

    @Test
    public void generateCity() {
        String str = faker.phoneNumber().subscriberNumber(10);
        System.out.println(str);
    }

    @Test
    public void successfulRegistrationTest() {
        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                genter = faker.options().option("Male", "Female", "Other"),
                phone = faker.phoneNumber().subscriberNumber(10),
                subject = faker.options().option("History", "Arts", "Biology", "Maths", "Commerce",
                "Social Studies", "Civics", "Hindi"),
                hobbies = faker.options().option("Sports", "Reading", "Music"),
                fileName = "test_pic.jpg",
                address = faker.address().fullAddress(),
                state = generateData.generateState(),
                city = generateData.generateCity(state);
        String[] calendarDate = generateData.generateCalendarDate();

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGenterWrapper(genter)
                .setPhone(phone)
                .setCalendar(calendarDate[0], calendarDate[1], calendarDate[2])
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
                .verifyResult("Date of Birth", calendarDate[0] + " " + calendarDate[1] + "," + calendarDate[2])
                .verifyResult("Subjects", subject)
                .verifyResult("Hobbies", hobbies)
                .verifyResult("Picture", fileName)
                .verifyResult("Address", address);
    }

    @Test
    public void shouldHaveStateTest() {
        /** Тест Xpath для проверки наличия строк в меню State */
        String[] expected = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        practiceFormPage.openPage();
        $x("//div[contains(text(), 'Select State')]").click();

        String getCity = $x("//div[contains(@class, 'css-26l3qy-menu')]").getText();
        String[] cityArray = getCity.split("\\n");

        assertArrayEquals(expected, cityArray, "Название городов не совпадает");
    }
}
