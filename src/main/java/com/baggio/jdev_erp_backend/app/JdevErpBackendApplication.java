package com.baggio.jdev_erp_backend.app;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
public class JdevErpBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdevErpBackendApplication.class, args);
	}

	@Bean(name = "cacheManager")
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cache = new ConcurrentMapCacheManager("cacheJdevErp");
		return cache;
	}
	
	@PostConstruct
	private void configTimeZone() {
		Locale.setDefault(Locale.forLanguageTag("pt_BR"));
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		Calendar.getInstance().setTimeZone(tz);
	}
}
