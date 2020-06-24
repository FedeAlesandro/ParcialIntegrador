package edu.utn.parcial.controllers;

import edu.utn.parcial.models.PhoneCallDtoResponse;
import edu.utn.parcial.services.RestTemplateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestTemplateController {

    private final RestTemplateService restTemplateService;

    @GetMapping
    @ApiOperation(value = "Get by duration", notes = "Returns the phone calls between the durations given by params")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "When doesn't exist phone calls between this durations"),
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "When exists phone calls between this durations") })
    public ResponseEntity<PhoneCallDtoResponse[]> getByDuration(@ApiParam(name =  "sinceDuration",
                                                                type = "Integer",
                                                                value = "The duration since the call will be searched",
                                                                example = "10",
                                                                required = true) @RequestParam(value = "sinceDuration") Integer sinceDuration,
                                                                @ApiParam(name =  "toDuration",
                                                                        type = "Integer",
                                                                        value = "The last duration the call will be searched",
                                                                        example = "20",
                                                                        required = true)  @RequestParam(value = "toDuration") Integer toDuration){

        return restTemplateService.getByDuration(sinceDuration, toDuration);
    }
}
