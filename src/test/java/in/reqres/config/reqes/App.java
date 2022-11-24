package in.reqres.config.reqes;

import org.aeonbits.owner.ConfigFactory;

public class App {
    public static AppConfig config = ConfigFactory.create(AppConfig.class, System.getProperties());
}
