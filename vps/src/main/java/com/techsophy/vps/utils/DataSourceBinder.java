package com.techsophy.vps.utils;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DataSourceBinder {
	@Bean(name = "vps")
	@ConfigurationProperties("spring.datasource.vps")
	public DataSource vpsDataSource() {
		return DataSourceBuilder.create().build();
	}
}
