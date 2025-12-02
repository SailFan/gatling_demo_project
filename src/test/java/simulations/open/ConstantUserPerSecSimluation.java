package simulations.open;



import ApiEndpoints.APiEndpoints;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

// ConstantUserPerSec 用于模拟以恒定的速度注入用户的策略，适用于稳定负载，PS.每秒20个新用户进入系统
public class ConstantUserPerSecSimluation extends Simulation {


    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://localhost:9527/");

    ScenarioBuilder scenarioBuilder = scenario("ConstantUserPerSecSimluation")
            .exec(APiEndpoints.load.check(bodyString().saveAs("bodyString")));


    {
        setUp(
                scenarioBuilder.injectOpen(constantUsersPerSec(20).during(20))
        ).protocols(httpProtocolBuilder);
    }

}
