package pe.joedayz.restapis.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import pe.joedayz.restapis.services.NationalityRestTemplateService;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(NationalityRestTemplateService.class)
public class NationalityRestTemplateServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NationalityRestTemplateService nationalityRestTemplateService;

    private MockRestServiceServer server;

    @BeforeEach
    public void init(){
        server = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void givenSearchIsMocked_whenPredictNationality_thenReceiveTheResponse(){
        //given
        this.server.expect(requestTo("https://api.nationalize.io?name=John"))
                .andRespond(withSuccess("{'name':'John', 'country': [{'country_id': 'US', 'probability': 0.9}]}",
                        MediaType.APPLICATION_JSON));

        //when
        String userServiceResponse = nationalityRestTemplateService.predictNationality("John");

        //then
        assertEquals("{'name':'John', 'country': [{'country_id': 'US', 'probability': 0.9}]}", userServiceResponse);
    }

}
