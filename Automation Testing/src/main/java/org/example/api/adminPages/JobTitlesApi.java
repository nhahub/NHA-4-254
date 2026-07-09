//package api;
//
//import io.restassured.response.Response;
//
//import static io.restassured.RestAssured.given;
//
//public class JobTitlesApi {
//
//    private final String cookie;
//    private final String token;
//
//    public JobTitlesApi(String cookie, String token) {
//        this.cookie = cookie;
//        this.token = token;
//    }
//
//    // GET ALL
//    public Response getAllJobTitles() {
//
//        return given()
//                .cookie("orangehrm", cookie)
//                .get("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/job-titles");
//    }
//
//    // GET BY ID
//    public Response getJobTitle(int id) {
//
//        return given()
//                .cookie("orangehrm", cookie)
//                .get("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/job-titles/" + id);
//    }
//
//    // CREATE
//    public Response createJobTitle(String body) {
//
//        return given()
//                .cookie("orangehrm", cookie)
//                .header("X-CSRF-TOKEN", token)
//                .contentType("application/json")
//                .body(body)
//                .post("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/job-titles");
//    }
//
//    // UPDATE
//    public Response updateJobTitle(int id, String body) {
//
//        return given()
//                .cookie("orangehrm", cookie)
//                .header("X-CSRF-TOKEN", token)
//                .contentType("application/json")
//                .body(body)
//                .put("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/job-titles/" + id);
//    }
//
//    // DELETE
//    public Response deleteJobTitle(int id) {
//
//        return given()
//                .cookie("orangehrm", cookie)
//                .header("X-CSRF-TOKEN", token)
//                .delete("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/job-titles/" + id);
//    }
//}


package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class JobTitlesApi {

    private final String cookie;

    public JobTitlesApi(String cookie) {
        this.cookie = cookie;
    }

    public Response getAllJobTitles() {

        return given()
                .cookie("orangehrm", cookie)
                .when()
                .get("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/job-titles");
    }
}