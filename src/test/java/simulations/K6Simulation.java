package simulations;

import ApiEndpoints.APiEndpoints;
import ApiEndpoints.AuthEndpoints;
import io.gatling.core.config.GatlingConfiguration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;


public class K6Simulation extends Simulation {
    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://localhost:9527/");
    ScenarioBuilder scenarioBuilder = scenario("对比").exec(APiEndpoints.load);

    {
        setUp(scenarioBuilder.injectOpen(atOnceUsers(10))).protocols(httpProtocolBuilder);
    }
}
