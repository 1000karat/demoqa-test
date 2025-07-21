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

###### 17.07.2025  
1. Добавлен тест негативных вводимых значений для поля "Mobile". Прописан в gradle.yml, в .appveyor.yml не используется.

###### 21.07.2025  
1. Удалено всё лишнее из ветки. Ветка для запуска на jenkins.  
2. Добавлены System.getProperty и настроен jenkins на выбор параметров запуска.  
   <img src="https://github.com/1000karat/demoqa-test/raw/jenkins/result-test/jenkins_param.png" width="45%" height="45%">