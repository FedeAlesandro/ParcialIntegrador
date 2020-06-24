package edu.utn.parcial.controllers;

import edu.utn.parcial.models.PhoneCallDtoResponse;
import edu.utn.parcial.services.RestTemplateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestTemplateControllerTest {

    @Mock
    RestTemplateService restTemplateService;

    RestTemplateController restTemplateController;

    @BeforeEach
    public void setUp(){
        initMocks(this);
        restTemplateController = new RestTemplateController(restTemplateService);
    }

    @Test
    public void getByDuration(){
        PhoneCallDtoResponse[] responses = {new PhoneCallDtoResponse()};
        ResponseEntity<PhoneCallDtoResponse[]> phoneCalls = ResponseEntity.of(Optional.of(responses));
        when(restTemplateService.getByDuration(1, 10)).thenReturn(phoneCalls);

        Assertions.assertEquals(phoneCalls, restTemplateController.getByDuration(1, 10));
    }
}
