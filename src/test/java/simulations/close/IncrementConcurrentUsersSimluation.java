package simulations.close;


import ApiEndpoints.APiEndpoints;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class IncrementConcurrentUsersSimluation extends Simulation {

    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://localhost:9527/");
    ScenarioBuilder scenarioBuilder = scenario("IncrementConcurrentUsersSimluation")
            .exec(APiEndpoints.load.check(bodyString().saveAs("response")));




    {
        setUp(
                scenarioBuilder.injectClosed(
                        incrementConcurrentUsers(10).
                                times(5).
                                eachLevelLasting(20000)
                                .separatedByRampsLasting(5000)
                                .startingFrom(5)
                )
        ).protocols(httpProtocolBuilder);
    }





}
