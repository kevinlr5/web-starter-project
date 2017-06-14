package org.krich.starter.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/info")
@RestController
public class StarterController {

  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public StarterResponse info() {
    return new StarterResponse("info");
  }

}