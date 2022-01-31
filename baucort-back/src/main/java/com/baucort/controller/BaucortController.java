package com.baucort.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BaucortController {
	
	@GetMapping()
	public String test() {
		return "HOLA MI AMOR MIRA ESTO ES LO DE MI PAPA";
	}

}
