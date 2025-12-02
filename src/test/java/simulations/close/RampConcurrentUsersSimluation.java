package simulations.close;


import ApiEndpoints.APiEndpoints;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

//每个用户执行完 scenario 后立即重新开始（循环执行），直到注入阶段结束
//
public class RampConcurrentUsersSimluation extends Simulation {
    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://localhost:9527/");
    ScenarioBuilder scenarioBuilder = scenario("constantCurrentUsersSimulationExample")
            .exec(APiEndpoints.load.check(bodyString().saveAs("response")));

    {
        setUp(
            scenarioBuilder.injectClosed(
                    rampConcurrentUsers(10).to(20).during(10)
            )
        ).protocols(httpProtocolBuilder);
    }
}
