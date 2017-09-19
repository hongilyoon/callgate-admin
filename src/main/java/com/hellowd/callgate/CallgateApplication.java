package com.hellowd.callgate;

import com.hellowd.callgate.core.annotation.modelMapper.EnableModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableModelMapper
@SpringBootApplication
public class CallgateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallgateApplication.class, args);
	}
}
