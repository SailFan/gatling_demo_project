package groups;

import ApiEndpoints.AuthEndpoints;
import io.gatling.javaapi.core.ChainBuilder;
import static io.gatling.javaapi.core.CoreDsl.*;



public class UserJourneyGroup {
    public static ChainBuilder createUserJourneyGroup(){
        return group("Login").on(
          exec(AuthEndpoints.auth)
        );
    }

}
