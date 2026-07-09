//package api;
//
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class JobTitlesApiTest {
//
//    private JobTitlesApi jobTitlesApi;
//
//    @BeforeMethod
//    public void setup() {
//        AuthApi auth = new AuthApi();
//        auth.login();
//        jobTitlesApi = new JobTitlesApi(auth.getCookie(), auth.getToken());
//    }
//
//    @Test
//    public void verifyGetAllJobTitles() {
//        Response response = jobTitlesApi.getAllJobTitles();
//        System.out.println(response.prettyPrint());
//        Assert.assertTrue(response.statusCode() == 200, "Expected status code 200");
//        Assert.assertTrue(response.asString().contains("data"), "Job titles list not returned"
//        );
//    }
//
//
//    @Test
//    public void verifyGetSingleJobTitle() {
//
//        Response response = jobTitlesApi.getJobTitle(1);
//        System.out.println(response.prettyPrint());
//        Assert.assertTrue(response.statusCode() == 200, "Failed to get job title");
//    }
//
//
//    @Test
//    public void verifyCreateJobTitle() {
//        String body =
//                """
//                        {
//                            "title":"API Automation Job Title",
//                            "description":"Created by Rest Assured",
//                            "note":"Test Data"
//                        }
//                        """;
//        Response response = jobTitlesApi.createJobTitle(body);
//        System.out.println(response.prettyPrint());
//        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 201,
//                "Job Title was not created");
//    }
//
//
//    @Test
//    public void verifyDeleteJobTitle() {
//        Response response = jobTitlesApi.deleteJobTitle(1);
//        System.out.println(response.prettyPrint());
//        Assert.assertTrue(response.statusCode() == 200,
//                "Job Title deletion failed"
//        );
//    }
//}


package api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.AuthApi;
import api.JobTitlesApi;

public class JobTitlesApiTest {

    private JobTitlesApi jobTitlesApi;

    @BeforeMethod
    public void setup() {

        AuthApi auth = new AuthApi();

        auth.login();

        jobTitlesApi =
                new JobTitlesApi(
                        auth.getCookie()
                );
    }

    @Test
    public void verifyGetAllJobTitles() {

        Response response =
                jobTitlesApi.getAllJobTitles();

        System.out.println(response.prettyPrint());

        Assert.assertTrue(
                response.statusCode() == 200,
                "Expected status code 200"
        );

        Assert.assertTrue(
                response.jsonPath()
                        .getList("data")
                        .size() > 0,
                "Job titles list is empty"
        );
    }
}