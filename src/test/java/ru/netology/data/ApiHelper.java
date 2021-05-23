package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class ApiHelper {

    static String url = System.getProperty("sut.url");

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(url.split(":")[0] + ":" + url.split(":")[1])
            .setPort(Integer.parseInt(url.split(":")[2].split("/")[0]))
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String paymentPageForm(Data.CardData cardData) {
        return given()
                .spec(requestSpec)
                .body(cardData)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract().response().asString();
    }

    public static String creditRequestPageForm(Data.CardData cardData) {
        return given()
                .spec(requestSpec)
                .body(cardData)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200)
                .extract().response().asString();
    }


}