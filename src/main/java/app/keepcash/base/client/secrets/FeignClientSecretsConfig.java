package app.keepcash.base.client.secrets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import app.keepcash.base.client.FeignCustomErrorDecoder;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.slf4j.Slf4jLogger;

public class FeignClientSecretsConfig {
	
	@Value("${secrets.username}")
	private String userName;

	@Value("${secrets.password}")
	private String password;
	
	public FeignClientSecretsConfig() {
		super();
	}

	@Bean
	Logger feignLoggerLevel() {
		return new Slf4jLogger();
	}

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(this.userName, this.password);
	}

	@Bean
	public RequestInterceptor requestLoggingInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate template) {
				template.header("Content-Type", "application/json");
			}
		};
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new FeignCustomErrorDecoder();
	}

}
