package pe.joedayz.restapis.unit;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.joedayz.restapis.services.NationalityWebClientService;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NationalityWebClientServiceTest {

    private static MockWebServer webServer;

    private NationalityWebClientService nationalityService;

    @BeforeAll
    public static void init() throws IOException{
        webServer = new MockWebServer();
        webServer.start();
    }

    @AfterAll
    public static void shutdown() throws IOException{
        webServer.shutdown();
    }

    @BeforeEach
    public void initialize(){
        nationalityService = new NationalityWebClientService();
    }

    @Test
    public void givenWebServerIsMocked_whenPredictNationality_thenReceiveTheResponse() throws InterruptedException {
        //access to the web server locally
        String baseUrl = String.format("http://127.0.0.1:%s", webServer.getPort());

        String expectedBody = "{'name':'John', 'country': [{'country_id': 'US', 'probability': 0.9}]}";

        //given
        webServer.enqueue(new MockResponse()
                .setBody(expectedBody)
                .addHeader("Content-Type", "application/json"));

        //when
        Mono<String> stringMono = nationalityService.predictNationality(baseUrl, "John");

        //then
        StepVerifier.create(stringMono)
                .expectNextMatches(resp -> resp.toLowerCase().equals(expectedBody.toLowerCase()))
                .verifyComplete();

        RecordedRequest recordedRequest = webServer.takeRequest();

        //asssertions
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/?name=John", recordedRequest.getPath());

    }

}
