package in.reqres.tests.specs;

import in.reqres.config.reqes.App;
import in.reqres.tests.components.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class FullSpecs {
    public static final RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri(App.config.apiUrl())
            .addFilter(withCustomTemplates())
            .log(LogDetail.ALL)
            .setContentType(JSON)
            .build();

    public static ResponseSpecification baseResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification baseResponseSpecToken = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectBody("token", notNullValue())
            .build();
}
