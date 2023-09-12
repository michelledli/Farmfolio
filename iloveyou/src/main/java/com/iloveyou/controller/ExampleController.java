package com.iloveyou.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ExampleController {
    @RequestMapping(value = "/example")
	public String index() {
		return "index";
	}
}
