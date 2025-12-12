package simulations;

import ApiEndpoints.APiEndpoints;
import common.RandomProductIterator;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;



import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;
import java.util.Iterator;
import java.util.Map;

public class CpuStressTestSimulation extends Simulation {
    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://localhost:9527/")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
            .userAgentHeader("Gatling CPU Stress Test");
    Iterator<Map<String, Object>> orderFeeder = new RandomProductIterator();

    ScenarioBuilder scenarioBuilder = scenario("CPU压力测试")
            .feed(orderFeeder)
            .exec(APiEndpoints.cpuStress);
    {
        setUp(
                scenarioBuilder.injectOpen(
                        constantUsersPerSec(1).during(Duration.ofSeconds(1)),
                        constantUsersPerSec(10).during(Duration.ofSeconds(120)),
                        constantUsersPerSec(20).during(Duration.ofSeconds(120)),
                        constantUsersPerSec(30).during(Duration.ofSeconds(120)),
                        constantUsersPerSec(40).during(Duration.ofSeconds(120)),
                        constantUsersPerSec(50).during(Duration.ofSeconds(120)),
                        constantUsersPerSec(60).during(Duration.ofSeconds(120))
//                        constantUsersPerSec(70).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(80).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(90).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(100).during(Duration.ofSeconds(120))
                        )
        ).protocols(httpProtocolBuilder);
    }


}

