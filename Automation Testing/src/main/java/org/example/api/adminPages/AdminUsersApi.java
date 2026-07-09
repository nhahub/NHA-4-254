// package api;

// import io.restassured.response.Response;

// import static io.restassured.RestAssured.given;

// public class AdminUsersApi {

//     private final String cookie;
//     private final String token;

//     public AdminUsersApi(String cookie, String token) {
//         this.cookie = cookie;
//         this.token = token;
//     }

//     public Response getAllUsers() {

//         return given()
//                 .cookie("orangehrm", cookie)
//                 .get("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/users");
//     }

//     public Response createUser(String body) {

//         return given()
//                 .cookie("orangehrm", cookie)
//                 .header("X-CSRF-TOKEN", token)
//                 .contentType("application/json")
//                 .body(body)
//                 .post("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/users");
//     }

//     public Response updateUser(int userId, String body) {

//         return given()
//                 .cookie("orangehrm", cookie)
//                 .header("X-CSRF-TOKEN", token)
//                 .contentType("application/json")
//                 .body(body)
//                 .patch("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/users/" + userId);
//     }

//     public Response deleteUser(int userId) {

//         return given()
//                 .cookie("orangehrm", cookie)
//                 .header("X-CSRF-TOKEN", token)
//                 .delete("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/users/" + userId);
//     }
// }



package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AdminUsersApi {

    private final String cookie;

    public AdminUsersApi(String cookie) {
        this.cookie = cookie;
    }

    public Response getAllUsers() {

        return given()
                .cookie("orangehrm", cookie)
                .when()
                .get("https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/admin/users");
    }
}