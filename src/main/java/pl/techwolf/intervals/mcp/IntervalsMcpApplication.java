package pl.techwolf.intervals.mcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IntervalsMcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntervalsMcpApplication.class, args);
	}

}
