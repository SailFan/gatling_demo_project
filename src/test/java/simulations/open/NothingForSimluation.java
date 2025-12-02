package simulations.open;


import ApiEndpoints.APiEndpoints;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
public class NothingForSimluation extends Simulation{

    HttpProtocolBuilder httpProtocolBuilder = http.baseUrl("http://localhost:9527/");
    ScenarioBuilder scenarioBuilder = scenario("NothingForSecSimluation")
            .exec(APiEndpoints.load.check(bodyString().saveAs("response")));



    {
        setUp(
                scenarioBuilder.injectOpen(
                        nothingFor(10)
                )
        ).protocols(httpProtocolBuilder);
    }

}
