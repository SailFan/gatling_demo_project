package simulations;// required for Gatling core structure DSL

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

// can be omitted if you don't use jdbcFeeder

// used for specifying durations with a unit, eg Duration.ofMinutes(5)


public class CyuanSimulation  extends  Simulation{

//    定义测试步骤
    ScenarioBuilder scenarioBuilder = scenario("CY").exec(http("模拟压测请求1").get("/api/data"));

//    定义全局HTTP协议参数
    HttpProtocolBuilder  httpProtocolBuilder = http.baseUrl("http://localhost:3000")
        .acceptHeader("application/json")
        .userAgentHeader("Gatling Java API");

    //开始运行
    {
        setUp(scenarioBuilder.injectOpen(
                constantUsersPerSec(2).during(10)
        )).protocols(httpProtocolBuilder);
    }
}

