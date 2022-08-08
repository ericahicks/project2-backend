package com.skillstorm.hotel.controller.TestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

@GetMapping("/")
public String health() {
  return "Hello & Welcome to CloudKatha !!!";
}
}