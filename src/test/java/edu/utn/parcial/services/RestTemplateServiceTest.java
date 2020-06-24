package edu.utn.parcial.services;

import edu.utn.parcial.models.PhoneCallDtoResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestTemplateServiceTest {

    @Mock
    private RestTemplate restTemplate;

    RestTemplateService restTemplateService;

    @BeforeEach
    public void setUp(){
        initMocks(this);
        restTemplateService = new RestTemplateService(restTemplate);
    }

    @Test
    public void getByDurationOk(){
        String url = "http://localhost:8081/calls/duration/?sinceDuration={sinceDuration}&toDuration={toDuration}";
        PhoneCallDtoResponse[] responses = {new PhoneCallDtoResponse()};

        when(restTemplate.exchange(url, HttpMethod.GET, null, PhoneCallDtoResponse[].class, 1, 10))
                .thenReturn(new ResponseEntity(responses, HttpStatus.OK));

        Assertions.assertEquals(responses, restTemplateService.getByDuration(1, 10).getBody());
    }

    @Test
    public void getByDurationNoContent(){
        String url = "http://localhost:8081/calls/duration/?sinceDuration={sinceDuration}&toDuration={toDuration}";
        PhoneCallDtoResponse[] responses = {};

        when(restTemplate.exchange(url, HttpMethod.GET, null, PhoneCallDtoResponse[].class, 1, 10))
                .thenReturn(new ResponseEntity(responses, HttpStatus.NO_CONTENT));

        Assertions.assertEquals(responses, restTemplateService.getByDuration(1, 10).getBody());
    }
}
