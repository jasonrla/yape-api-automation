package org.yape.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import static io.restassured.RestAssured.given;

public class APIUtils {

    public static Response createToken(String username, String password) {
        return given()
                .contentType("application/json")
                .body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}")
                .post("/auth");
    }

    public static Response getBookingIds() {
        return given()
                .get("/booking");
    }

    public static Response postBookingIds() {
        return given()
                .contentType(ContentType.JSON)
                .post("/booking");
    }

    public static Response getBookingIds(String firstname, String lastname) {
        return given()
                .queryParam("firstname", firstname)
                .queryParam("lastname", lastname)
                .get("/booking");
    }

    public static Response getBookingIds(int checkin, int checkout) {
        return given()
                .queryParam("checkin", checkin)
                .queryParam("checkout", checkout)
                .get("/booking");
    }

    public static Response getBookingIds(String firstname, String lastname, int checkin, int checkout) {
        return given()
                .queryParam("firstname", firstname)
                .queryParam("lastname", lastname)
                .queryParam("checkin", checkin)
                .queryParam("checkout", checkout)
                .get("/booking");
    }

    public static Response getBookingById(int bookingId) {
        return given()
                .get("/booking/" + bookingId);
    }

    public static Response createBooking(@NotNull JsonObject request) {
        return given()
                .contentType("application/json")
                .body(request.toString())
                .post("/booking");
    }

    public static Response updateBooking(int id, JsonObject request, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .body(request.toString())
                .when()
                .put("/booking/" + id);
    }

    public static Response partialUpdateBooking(int bookingId, String firstname, String lastname) {
        JsonObject bookingDetails = new JsonObject();
        bookingDetails.addProperty("firstname", firstname);
        bookingDetails.addProperty("lastname", lastname);

        return given()
                .contentType("application/json")
                .body(bookingDetails.toString())
                .patch("/booking/" + bookingId);
    }

    public static Response deleteBooking(int bookingId, String token) {
        return given()
                .header("Cookie", "token=" + token)
                .delete("/booking/" + bookingId);
    }

    public static Response pingHealthCheck() {
        return given()
                .get("/ping");
    }
}
