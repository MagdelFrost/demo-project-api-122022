package in.reqres.tests;

import in.reqres.tests.model.ResponseModel;
import static com.codeborne.selenide.Selenide.open;
import in.reqres.tests.specs.FullSpecs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.tests.specs.FullSpecs.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class MainApiTests extends BaseTest {

    @Test
    @DisplayName("Тест соединения")
    public void connectionTest() {
        given()
                .spec(connectionRequestSpec)
                .when()
                .get()
                .then()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem(testData.getEmail()))
                .spec(connectionResponseSpec);
    }

    @Test
    @DisplayName("Тест регистрации позитивный")
    public void registrationPositiveTest() {
        ResponseModel response = given()
                .spec(FullSpecs.registrationRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(positiveResponseSpec)
                .extract()
                .as(ResponseModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    @DisplayName("Тест входа позитивный")
    public void loginPositiveTest() {
        ResponseModel response = given()
                .spec(loginRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(positiveResponseSpec)
                .extract()
                .as(ResponseModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    @DisplayName("Тест регистрации негативный")
    public void registrationNegativeTest() {

        given()
                .spec(registrationRequestSpec)
                .body(bodyWrong)
                .when()
                .post()
                .then()
                .spec(negativeResponseSpec)
                .extract()
                .as(ResponseModel.class);
    }

    @Test
    @DisplayName("Тест входа негативный")
    public void loginNegativeTest() {

        given()
                .spec(loginRequestSpec)
                .body(bodyWrong)
                .when()
                .post()
                .then()
                .spec(negativeResponseSpec)
                .extract()
                .as(ResponseModel.class);
    }
}
