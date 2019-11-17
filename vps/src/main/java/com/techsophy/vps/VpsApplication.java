package com.techsophy.vps;

import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class VpsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		return springApplicationBuilder.sources(VpsApplication.class).properties(getProperties());
	}

	public static void main(String[] args) {

		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(VpsApplication.class);
		springApplicationBuilder.sources(VpsApplication.class).properties(getProperties()).run(args);
	}

	static Properties getProperties() {
		Properties props = new Properties();
		props.put("spring.config.location", "classpath:vps.properties");
		return props;
	}
}
