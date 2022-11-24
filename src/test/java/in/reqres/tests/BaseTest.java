package in.reqres.tests;

import in.reqres.tests.components.TestData;
import in.reqres.tests.model.BodyModel;

public class BaseTest {

    TestData testData = new TestData();

    BodyModel body = new BodyModel(testData.getEmail(), testData.getPassword());
    BodyModel bodyWrong = new BodyModel(testData.getEmailWrong(), testData.getPasswordWrong());
}
