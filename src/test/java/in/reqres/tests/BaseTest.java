package in.reqres.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import in.reqres.config.Project;
import in.reqres.config.reqes.App;
import in.reqres.helpers.AllureAttachments;
import in.reqres.helpers.DriverSettings;
import in.reqres.helpers.DriverUtils;
import in.reqres.tests.components.TestData;
import in.reqres.tests.model.BodyModel;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({AllureJunit5.class})
public class BaseTest {
    @BeforeAll
    static void beforeAll() {
        DriverSettings.configure();
        RestAssured.baseURI = App.config.apiUrl();
        Configuration.baseUrl = App.config.webUrl();
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }


    @AfterEach
    public void afterEach() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
        AllureAttachments.addBrowserConsoleLogs();

        Selenide.closeWebDriver();

        if (Project.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }
    }
    TestData testData = new TestData();

    BodyModel body = new BodyModel(testData.getEmail(), testData.getPassword());
    BodyModel bodyWrong = new BodyModel(testData.getEmailWrong(), testData.getPasswordWrong());
}
