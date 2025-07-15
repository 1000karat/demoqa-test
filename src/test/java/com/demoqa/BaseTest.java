package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.helper.AttachForTest;
import com.demoqa.helper.GenerateData;
import com.demoqa.pages.PracticeFormPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@Tags({@Tag("UI_TEST"), @Tag("remote")})
@Owner("1000karat")
@Feature("Student Registration Form")
public class BaseTest {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    GenerateData generateData = new GenerateData();
    Faker faker = new Faker();

    @AfterAll
    static void afterAll() {
        closeWebDriver();
        AttachForTest.screenshotAs("LastScreenshot");
        AttachForTest.pageSource();
        AttachForTest.browserConsoleLogs();
        AttachForTest.addVideo();
    }

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1366x1085";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.headless = false;
        Configuration.browser = "chrome";
        Configuration.browserVersion = "128.0";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("allure", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @Story("Отправка заполненной формы")
    @DisplayName("Registration Form Test")
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
                .verifyResult("Address", address)
                .verifyResult("State and City", state + " " + city);
    }


    @Story("State")
    @DisplayName("State expected")
    @Test
    public void shouldHaveStateTest() {
        /** Тест Xpath для проверки наличия строк в меню State */
        String[] expected = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        step("Открываем страницу", () -> {
            practiceFormPage.openPage();
        });
        step("Нажать 'State'", () -> {
            $x("//div[contains(text(), 'Select State')]").click();
        });

        String getState = $x("//div[contains(@class, 'css-26l3qy-menu')]").getText();
        String[] stateArray = getState.split("\\n");

        assertArrayEquals(expected, stateArray, "Название штатов не совпадает");
    }
}
