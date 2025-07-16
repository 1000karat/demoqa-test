[![Build status](https://ci.appveyor.com/api/projects/status/okh73s4b1r6c36gd?svg=true)](https://ci.appveyor.com/project/1000karat/demoqa-test) - [![Java CI with Gradle](https://github.com/1000karat/demoqa-test/actions/workflows/gradle.yml/badge.svg)](https://github.com/1000karat/demoqa-test/actions/workflows/gradle.yml)

#### Changelog
###### 11.07.2025 
1. Создание репозитория demoqa-test для создания тестов https://demoqa.com/automation-practice-form  
2. Используется: Java 11  
3. Добавление CI [AppVeyor](https://www.appveyor.com)

###### 13.07.2025
1. Создана ветка helper  
2. Подключена библиотека datafaker  
3. Создан класс [GenerateData.java](src/test/java/com/demoqa/helper/GenerateData.java), где реализована генерация значений даты календаря, state, city.  

###### 14.07.2025
1. Заполнение календаря вынесено в отдельный класс [CalendarComponent.java](src/test/java/com/demoqa/helper/components/CalendarComponent.java)
2. Добавлен Allure Report
3. Добавлены аннотации @Step
4. Запуск тестов: `./gradlew clean test allureServe`

###### 15.07.2025
1. Мелкие правки  
2. Создана ветка jenkins  
3. Добавлен Configuration.remote
4. Добавлен класс [AttachForTest.java](src/test/java/com/demoqa/helper/AttachForTest.java) для добавления содержимого страницы в Allure  
5. Тест запускается удалённо на https://jenkins.autotests.cloud/ 
6. pull request jenkins to main
7. Скриншоты Allure Report и видео прохождения теста:  
<img src="https://github.com/1000karat/demoqa-test/raw/jenkins/result-test/Screenshot_1.png" width="45%" height="45%"> - <img src="https://github.com/1000karat/demoqa-test/raw/jenkins/result-test/Screenshot_2.png" width="45%" height="45%">  
<img src="https://github.com/1000karat/demoqa-test/raw/jenkins/result-test/Screenshot_3.png" width="45%" height="45%"> - <img src="https://github.com/1000karat/demoqa-test/raw/jenkins/result-test/Screenshot_4.png" width="45%" height="45%">  
<p align="center"><img src="https://github.com/1000karat/demoqa-test/raw/jenkins/result-test/7fadee20731cf29e420b70e7d5dcc43d.gif" width="45%" height="45%"></p> 

###### 16.07.2025
1. В [gradle.yml](.github/workflows/gradle.yml) Добавлен Allure github-pages доступный по адресу https://1000karat.github.io/demoqa-test/ 
2. Конфигурация "Configuration.remote":  
-- Для прохождения тестов в [Jenkins](https://jenkins.autotests.cloud) использовать:
```
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
```
-- [AppVeyor](https://www.appveyor.com) падает при указании:
```
        Configuration.remote = "http://localhost:4444/wd/hub";
```