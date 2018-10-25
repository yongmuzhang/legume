package org.zym.legume.skeleton.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zym
 * @date 18/5/12
 */
@Api(value = "第一例", tags = "第一例", protocols = "http", description = "API")
@RequestMapping("/helloWorld")
@RestController
public class HelloWorldController {

    /**
     * hello world!
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "向地球问好", notes = "您好，地球")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "x-token", value = "x-token", required = false, dataType = "string")
    })
    @RequestMapping(
            value = "/abc",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    Map<String, String> helloWold(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>(16);
        result.put("message", "Hello World!");
        throw new RuntimeException("不知道");
    }

}
