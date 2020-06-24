package edu.utn.parcial.services;

import edu.utn.parcial.models.PhoneCallDtoResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    private void init(){
        restTemplate = new RestTemplateBuilder()
                .build();
    }

    public ResponseEntity<PhoneCallDtoResponse[]> getByDuration(Integer sinceDuration, Integer toDuration){

        String url = "http://localhost:8081/calls/duration/?sinceDuration={sinceDuration}&toDuration={toDuration}";

        return restTemplate.exchange(url, HttpMethod.GET, null, PhoneCallDtoResponse[].class, sinceDuration, toDuration);
    }
}
