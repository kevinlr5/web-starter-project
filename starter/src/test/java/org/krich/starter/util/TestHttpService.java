package org.krich.starter.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Service
public class TestHttpService {

  private final WebApplicationContext webApplicationContext;
  private final ObjectMapper mapper;

  @Autowired
  public TestHttpService(
      WebApplicationContext webApplicationContext,
      ObjectMapper mapper) {
    this.webApplicationContext = webApplicationContext;
    this.mapper = mapper;
  }

  public MockMvc mvc() {
    return MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  public String serialize(Object object) {
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }
  }

  public <T> T deserialize(MvcResult result, Class<T> responseClass) {
    try {
      return mapper.readerFor(responseClass).readValue(result.getResponse().getContentAsString());
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

}
