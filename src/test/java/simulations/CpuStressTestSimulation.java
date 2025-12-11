package simulations;

import ApiEndpoints.APiEndpoints;
import common.RandomProductIterator;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


import static feeders.UserFeeder.userFeeder;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.FeederBuilder.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CpuStressTestSimulation extends Simulation {
    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://localhost:9527/")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
            .userAgentHeader("Gatling CPU Stress Test");
    Iterator<Map<String, Object>> orderFeeder = new RandomProductIterator();

    ScenarioBuilder scenarioBuilder = scenario("CPU压力测试")
            .exec(APiEndpoints.cpuStress)
            .feed(orderFeeder);
}
