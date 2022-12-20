package in.reqres.tests;

import in.reqres.tests.model.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static in.reqres.tests.specs.FullSpecs.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

public class LoginApiTests extends BaseTest {

    @Test
    @DisplayName("Тест соединения")
    public void connectionTest() {
        open();

        given()
                .spec(baseRequestSpec)
                .when()
                .get("/users")
                .then()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem(testData.getEmail()))
                .spec(baseResponseSpec)
                .statusCode(200);
    }

    @Test
    @DisplayName("Тест входа позитивный")
    public void loginPositiveTest() {
        open();

        ResponseModel response = given()
                .spec(baseRequestSpec)
                .body(body)
                .when()
                .post("/login")
                .then()
                .spec(baseResponseSpecToken)
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .as(ResponseModel.class);

        assertThat(response.getToken()).isNotNull();
    }

    @Test
    @DisplayName("Тест входа негативный")
    public void loginNegativeTest() {
        open();

        given()
                .spec(baseRequestSpec)
                .body(bodyWrong)
                .when()
                .post("/login")
                .then()
                .spec(baseResponseSpec)
                .statusCode(400)
                .extract()
                .as(ResponseModel.class);
    }
}
