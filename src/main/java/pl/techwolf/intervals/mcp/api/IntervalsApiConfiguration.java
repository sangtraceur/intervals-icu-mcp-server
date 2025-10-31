package pl.techwolf.intervals.mcp.api;

import feign.RequestInterceptor;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class IntervalsApiConfiguration {

  private static final String USERNAME = "API_KEY";

  @Value("${intervals.api.token}")
  private String apiToken;

  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      String auth = USERNAME + ":" + apiToken;
      byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
      String authHeader = "Basic " + new String(encodedAuth);
      requestTemplate.header("Authorization", authHeader);
    };
  }

  @Bean
  public HttpMessageConverters messageConverters() {
    return new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
  }
}
