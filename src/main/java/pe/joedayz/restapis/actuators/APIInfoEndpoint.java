package pe.joedayz.restapis.actuators;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "apiInfo")
public class APIInfoEndpoint {

    @ReadOperation
    public String getAPIInfo() {
        return "The todo app expone 2 APIs - Todo and todoType";
    }
}
