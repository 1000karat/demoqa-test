###### 15.07.2025
1. Скриншоты Allure Report и видео прохождения теста:  
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
###### 21.07.2025  
1. Удалено всё лишнее из ветки. Ветка для запуска на Jenkins.  
2. Добавлены System.getProperty и настроен jenkins на выбор параметров запуска.  
   <img src="https://github.com/1000karat/demoqa-test/raw/jenkins/result-test/jenkins_param.png" width="45%" height="45%">

###### 23.07.2025  
1. Описание Jenkins job Configuration:
- добавлены параметры: TASK, BROWSER, BROWSER_VERSION, BROWSER_SIZE, REMOTE
- строка запуска gradle:  
``` 
- clean ${TASK} "-Dbrowser=${BROWSER}" "-DbrowserVersion=${BROWSER_VERSION}" "-Dremote=${remote}"  "-DbrowserSize=${BROWSER_SIZE}"
```
- добавлен шаг "Create/Update Text File", содержимое:  
``` {"base":{"logo":"","project":"${JOB_BASE_NAME}","environment":"","comment":"гляди что получилось!","reportLink":"/${BUILD_URL}","language":"ru","allureFolder":"allure-report","enableChart":true,"enableSuitesPublishing":false,"customData":{}},"telegram":{"token":";"${TELEGRAM_BOT_TOKEN}","chat":"${TELEGRAM_CHAT_ID}","topic":"","replyTo":"","templatePath":"/templates/telegram.ftl"}}```  

- добавлен шаг "Post build task", содержимое:    
```
wget -P notifications https://github.com/qa-guru/allure-notifications/releases/download/4.9.0/allure-notifications-4.9.0.jar  
chmod +x notifications/allure-notifications-4.9.0.jar  
java "-DconfigFile=notifications/config.json" -jar notifications/allure-notifications-4.9.0.jar  
rm notifications/*.*
```
Screeen:  
<img src="result-test/jenkins_tg_bot_message.png" width="25%" height="25%">