package com.demoqa.helper.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final static SelenideElement
            dateOfBirthInput = $("#dateOfBirthInput"),
            setMonth = $(".react-datepicker__month-select"),
            setYear = $(".react-datepicker__year-select");

    public CalendarComponent(String valueDay, String valueMonth, String valueYear) {
        dateOfBirthInput.click();
        setMonth.selectOption(valueMonth);
        setYear.selectOption(valueYear);
        $(".react-datepicker__day--0" + valueDay + ":not(.react-datepicker__day--outside-month)").click();
    }
}
