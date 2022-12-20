package in.reqres.tests;

import in.reqres.tests.model.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static in.reqres.tests.specs.FullSpecs.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegistrationApiTests extends BaseTest {

    @Test
    @DisplayName("Тест регистрации позитивный")
    public void registrationPositiveTest() {
        open();

        ResponseModel response = given()
                .spec(baseRequestSpec)
                .body(body)
                .when()
                .post("/register")
                .then()
                .spec(baseResponseSpecToken)
                .statusCode(200)
                .extract()
                .as(ResponseModel.class);

        assertThat(response.getToken()).isNotNull();
    }

    @Test
    @DisplayName("Тест регистрации негативный")
    public void registrationNegativeTest() {
        open();

        given()
                .spec(baseRequestSpec)
                .body(bodyWrong)
                .when()
                .post("/register")
                .then()
                .spec(baseResponseSpec)
                .statusCode(400)
                .extract()
                .as(ResponseModel.class);
    }
}
