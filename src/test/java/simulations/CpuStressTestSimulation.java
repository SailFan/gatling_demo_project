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
//                        constantUsersPerSec(1).during(Duration.ofSeconds(1)),
//                        constantUsersPerSec(10).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(20).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(30).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(40).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(50).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(60).during(Duration.ofSeconds(120))
//                        constantUsersPerSec(70).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(80).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(90).during(Duration.ofSeconds(120)),
//                        constantUsersPerSec(100).during(Duration.ofSeconds(120))
                        rampUsersPerSec(0).to(1).during(Duration.ofMinutes(1)),
                        constantUsersPerSec(1).during(Duration.ofMinutes(3)),
                        rampUsersPerSec(1).to(2).during(Duration.ofMinutes(1)),
                        constantUsersPerSec(2).during(Duration.ofMinutes(3)),
                        rampUsersPerSec(2).to(3).during(Duration.ofMinutes(1)),
                        constantUsersPerSec(3).during(Duration.ofMinutes(3)),
                        rampUsersPerSec(3).to(4).during(Duration.ofMinutes(1)),
                        constantUsersPerSec(4).during(Duration.ofMinutes(3))
                        )
        ).protocols(httpProtocolBuilder);
    }


}
// RPS 是因，并发是果；用 Gatling，你控制因，不直接控制果。
//核心判断逻辑（先说结论）
//
//业界通常遵循三条基本判断原则：
//
//吞吐不再线性增长，成功请求数不再等比例上升，甚至下降
//
//延迟出现拐点（Latency Knee）
//
//P95 / P99 响应时间在某个负载点开始陡增 而不是缓慢上升
//
//错误率开始显性上升 5xx、超时、连接失败等
//
//响应时间先恶化 → 吞吐停滞 → 错误率上升，这是最典型的演进路径。