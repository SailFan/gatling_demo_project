package data;
import io.gatling.javaapi.core.FeederBuilder;

import static io.gatling.javaapi.core.CoreDsl.csv;


public class UserDataFeeder {
    public static final FeederBuilder<String> userFeeder = csv("users.csv").circular();

}
