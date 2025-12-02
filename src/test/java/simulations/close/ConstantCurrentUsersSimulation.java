package simulations.close;



import ApiEndpoints.APiEndpoints;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

// 此方法用于定义在整个测试运行期间，保持恒定的并发用户数，Gatling 会保持一个固定的数量的用户活跃，直到测试结束
public class ConstantCurrentUsersSimulation extends Simulation {
    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://localhost:9527/");
    ScenarioBuilder scenarioBuilder = scenario("constantCurrentUsersSimulationExample")
            .exec(APiEndpoints.load.check(bodyString().saveAs("response")));


    {
        setUp(
            scenarioBuilder.injectClosed(constantConcurrentUsers(10).during(10))
        ).protocols(httpProtocolBuilder);
    }
}
