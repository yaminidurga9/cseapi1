package com.example.CseSbJenkins2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarController {
	@GetMapping("/rocks")
	public String cserocks() {
		return "welcome to spring boot war file";
	}
}
