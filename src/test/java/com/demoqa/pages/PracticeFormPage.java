package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.helper.components.CalendarComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private final static String URL_PATH = "/automation-practice-form";
    private final static String WRAPPER_PAGE_TEXT = "Student Registration Form";
    private final static String MODAL_TEXT = "Thanks for submitting the form";
    private final SelenideElement
            header = $(".practice-form-wrapper"),
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            genterWrapper = $("#genterWrapper"),
            phone = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            state = $("#state"),
            city = $("#city"),
            buttonSubmit = $("#submit"),
            modalHeader = $(".modal-header"),
            tableResult = $(".table-responsive");

    @Step("Открыть страницу: " + URL_PATH)
    public PracticeFormPage openPage() {
        open(URL_PATH);
        header.shouldHave(text(WRAPPER_PAGE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    @Step("Заполнить firstName: {value}")
    public PracticeFormPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    @Step("Заполнить lastName: {value}")
    public PracticeFormPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    @Step("Заполнить eMail: {value}")
    public PracticeFormPage setEmail(String value) {
        email.setValue(value);
        return this;
    }

    @Step("Выбрать пол: {value}")
    public PracticeFormPage setGenterWrapper(String value) {
        genterWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Заполнить номер: {value}")
    public PracticeFormPage setPhone(String value) {
        phone.setValue(value);
        return this;
    }

    @Step("Заполнить календарь: {day} {month} {year}")
    public PracticeFormPage setCalendar(String day, String month, String year) {
        CalendarComponent calendarComponent = new CalendarComponent(day, month, year);
        return this;
    }

    @Step("Заполнить тему: {value}")
    public PracticeFormPage setSubjectsInput(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Заполнить хобби: {value}")
    public PracticeFormPage setHobbiesWrapper(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }

    @Step("Загрузить фото: {fileName}")
    public PracticeFormPage setUploadPicture(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    @Step("Заполнить адрес: {value}")
    public PracticeFormPage setCurrentAddress(String value) {
        this.currentAddress.setValue(value);
        return this;
    }

    @Step("Выбрать штат: {valueState} и город: {valueCity}")
    public PracticeFormPage setStateAndSetCity(String valueState, String valueCity) {
        state.click();
        $("#stateCity-wrapper").$(byText(valueState)).click();
        city.click();
        $("#stateCity-wrapper").$(byText(valueCity)).click();
        return this;
    }

    @Step("Нажать 'отправить'")
    public PracticeFormPage buttonSubmit() {
        buttonSubmit.click();
        return this;
    }

    @Step("Проверка отображения всплывающего окна")
    public PracticeFormPage verifyResultModalText() {
        modalHeader.should(visible);
        modalHeader.shouldHave(text(MODAL_TEXT));
        return this;
    }

    @Step("Верефицировать отправленные данные в окне {key} {value}")
    public PracticeFormPage verifyResult(String key, String value) {
        tableResult.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }
}
