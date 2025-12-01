package ApiEndpoints;


import io.gatling.javaapi.http.HttpRequestActionBuilder;

import static io.gatling.javaapi.core.CoreDsl.ElFileBody;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class AuthEndpoints {

    public static final HttpRequestActionBuilder auth = http("User Login")
            .post("/api/login")
            .body(ElFileBody("bodies/login.json"))
            .asJson()
            .header("Content-Type", "application/json")
            .check(status().is(200))
            .check(jsonPath("$.token").saveAs("token"));

}