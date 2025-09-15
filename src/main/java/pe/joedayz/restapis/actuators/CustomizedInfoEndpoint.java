package pe.joedayz.restapis.actuators;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class CustomizedInfoEndpoint {

    private InfoEndpoint infoEndpoint;

    public CustomizedInfoEndpoint(InfoEndpoint infoEndpoint) {
        this.infoEndpoint = infoEndpoint;
    }

    @ReadOperation
    public WebEndpointResponse info(){
        Map<String, Object> info = this.infoEndpoint.info();
        String apiInfo = new APIInfoEndpoint().getAPIInfo();

        HashMap<String, Object> modifiedInfo = new HashMap<>();
        info.keySet().forEach(key -> modifiedInfo.put(key, info.get(key)));

        modifiedInfo.put("apiInfo", apiInfo);
        return new WebEndpointResponse<>(modifiedInfo, 200);
    }

}
