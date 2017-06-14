package org.krich.starter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class StarterApplication {

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(StarterApplication.class);
    application.setBannerMode(Mode.OFF);
    application.run(args);
  }

  @Bean
  public HttpMessageConverters customConverters() {
    MappingJackson2HttpMessageConverter jacksonConverter =
        new MappingJackson2HttpMessageConverter();
    jacksonConverter.setObjectMapper(getObjectMapper());
    return new HttpMessageConverters(jacksonConverter);
  }

  @Bean
  public ObjectMapper getObjectMapper() {
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
    objectMapper.disable(MapperFeature.AUTO_DETECT_CREATORS);
    objectMapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
    objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES);
    objectMapper.registerModule(new GuavaModule());
    return objectMapper;
  }

}
