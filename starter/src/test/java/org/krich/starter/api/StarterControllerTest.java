package org.krich.starter.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.krich.starter.util.StarterIntegrationBaseTest;
import org.krich.starter.util.TestHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

public class StarterControllerTest extends StarterIntegrationBaseTest {

  @Autowired
  private TestHttpService http;

  @Test
  public void testInfo() throws Exception {
    MvcResult result = http.mvc()
        .perform(get("/api/info"))
        .andExpect(status().isOk())
        .andReturn();
    StarterResponse response = http.deserialize(result, StarterResponse.class);
    StarterResponse expected = new StarterResponse("info");
    Assert.assertEquals(expected, response);
  }

}
