package simulations.open;

import io.gatling.javaapi.core.Simulation;
import ApiEndpoints.APiEndpoints;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;


// 第一秒n个用户，第60秒内递增到m个用户，60秒内总共新增m-n用户
public class rampUsersPerSecSimulation  extends Simulation{

    private static final Logger log = LoggerFactory.getLogger(rampUsersPerSecSimulation.class);
    HttpProtocolBuilder requestBuilder = http.baseUrl("http://localhost:9527/");
    ScenarioBuilder scenarioBuilder = scenario("rampUsersPerSecExample")
            .exec(APiEndpoints.load.check(bodyString().saveAs("responseBody")));
//            .exec(log("Response: ${responseBody}"));

    {
        setUp(
                scenarioBuilder.injectOpen(
                        rampUsersPerSec(10).to(50).during(10)
                )
        ).protocols(requestBuilder);
    }

}
