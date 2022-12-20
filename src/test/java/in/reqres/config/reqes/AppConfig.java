package in.reqres.config.reqes;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/reqes/app.properties"
})
public interface AppConfig extends Config {

    String apiUrl();

}
