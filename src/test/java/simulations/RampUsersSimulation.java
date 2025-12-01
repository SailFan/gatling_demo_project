package simulations;

import io.gatling.javaapi.core.Simulation;
import ApiEndpoints.APiEndpoints;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

//在指定时间内线性增加用户
public class RampUsersSimulation extends Simulation {


    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://localhost:9527/");

    ScenarioBuilder scenarioBuilder = scenario("RampUsersSimple")
            .exec(APiEndpoints.load.check(status().is(200)));


    {

        setUp(
                scenarioBuilder.injectOpen(
                        rampUsers(100).during(Duration.ofMinutes(1))
                )
        ).protocols(httpProtocolBuilder);
    }

}
