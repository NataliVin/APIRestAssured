import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class RestTest {
    @Test
    public void checkStatusCode() {
        given().baseUri("https://reqres.in")
                .basePath("/api/users/23")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(404);
    }


    @Test
    public void checkUserFirstName() {
        given().baseUri("https://reqres.in")
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .body("data[0].first_name", equalTo("George"));
    }

    @Test
    public void checkNumberOfUsers() {
        int expectedAmountOfUsers = 6;
        List<String> usersEmails = given().baseUri("https://reqres.in")
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("data.email");
        assertEquals(usersEmails.size(), expectedAmountOfUsers, "Amount of users is not as expected");
    }

    @Test
    public void checkThatUserWithExpectedEmailExists() {
        String expectedEmail = "george.bluth@reqres.in";
        List<UserProfileModel> users = given().baseUri("https://reqres.in")
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("data", UserProfileModel.class);
        assertThat(users).extracting(UserProfileModel::getEmail).contains(expectedEmail);
    }

    @Test
    public void createUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("James");
        userRequest.setJob("driver");
        UserResponse userResponse = given()
                .baseUri("https://reqres.in")
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .body(userRequest)
                .when().post()
                .then().extract().as(UserResponse.class);
        assertThat(userResponse)
                .isNotNull()
                .extracting(UserResponse::getName)
                .isEqualTo(userRequest.getName());
    }
}
