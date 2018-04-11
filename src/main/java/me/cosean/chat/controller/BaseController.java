package me.cosean.chat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Base APIs", tags = "Base")
public class BaseController {

    @ApiOperation(value = "Baseeeeee", notes = "helllooo", response = String.class)
    @GetMapping("/")
    public ResponseEntity reduction() {
        return ResponseEntity.ok("Hello");
    }
}
