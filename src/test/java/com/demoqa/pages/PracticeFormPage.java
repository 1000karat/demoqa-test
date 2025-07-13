package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private final static String URL_PATH = "/automation-practice-form";
    private final static String WRAPPER_PAGE_TEXT = "Student Registration Form";
    private final static String MODAL_TEXT = "Thanks for submitting the form";
    private SelenideElement
            header = $(".practice-form-wrapper"),
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            genterWrapper = $("#genterWrapper"),
            phone = $("#userNumber"),
            calendar = $("#dateOfBirthInput"),
            month = $(".react-datepicker__month-select"),
            year = $(".react-datepicker__year-select"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            state = $("#state"),
            city = $("#city"),
            buttonSubmit = $("#submit"),
            modalHeader = $(".modal-header"),
            tableResult = $(".table-responsive");

    public PracticeFormPage openPage() {
        open(URL_PATH);
        header.shouldHave(text(WRAPPER_PAGE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public PracticeFormPage setEmail(String value) {
        email.setValue(value);
        return this;
    }

    public PracticeFormPage setGenterWrapper(String value) {
        genterWrapper.$(byText(value)).click();
        return this;
    }

    public PracticeFormPage setPhone(String value) {
        phone.setValue(value);
        return this;
    }

    public PracticeFormPage setCalendar(String valueDay, String valueMonth, String valueYear) {
        calendar.click();
        month.selectOption(valueMonth);
        year.selectOption(valueYear);
        $(".react-datepicker__day--0" + valueDay + ":not(.react-datepicker__day--outside-month)").click();
        return this;
    }

    public PracticeFormPage setSubjectsInput(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public PracticeFormPage setHobbiesWrapper(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }

    public PracticeFormPage setUploadPicture(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    public PracticeFormPage setCurrentAddress(String value) {
        this.currentAddress.setValue(value);
        return this;
    }

    public PracticeFormPage setStateAndSetCity(String valueState, String valueCity) {
        state.click();
        $("#stateCity-wrapper").$(byText(valueState)).click();
        city.click();
        $("#stateCity-wrapper").$(byText(valueCity)).click();
        return this;
    }

    public PracticeFormPage buttonSubmit() {
        buttonSubmit.click();
        return this;
    }

    public PracticeFormPage verifyResultModalText() {
        modalHeader.should(visible);
        modalHeader.shouldHave(text(MODAL_TEXT));
        return this;
    }

    public PracticeFormPage verifyResult(String key, String value) {
        tableResult.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }
}
