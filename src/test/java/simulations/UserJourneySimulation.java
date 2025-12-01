package simulations;

import ApiEndpoints.APiEndpoints;
import ApiEndpoints.AuthEndpoints;
import io.gatling.core.config.GatlingConfiguration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
public class UserJourneySimulation extends Simulation {
    GatlingConfiguration config = GatlingConfiguration.load();
    {
        System.out.println("=== Gatling 配置检查 ===");
        System.out.println("启用的数据写入器: " + config.data().dataWriters());
        System.out.println("=========================");
    }
    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:3000")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
            .disableCaching();



    ScenarioBuilder scenario = scenario("获取SPU")
            .exec(AuthEndpoints.auth)
            .exec(APiEndpoints.spu)
            .exec(session -> {
                System.out.println("session token = " + session.getString("token"));
                return session;
            });
    {
        setUp(
                scenario.injectOpen(
                       constantUsersPerSec(1).during(10)
                )
        ).protocols(httpProtocol);
    }

}
