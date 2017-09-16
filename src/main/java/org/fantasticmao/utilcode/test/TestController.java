package org.fantasticmao.utilcode.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author maokeluo
 * @desc
 * @create 17-9-16
 */
@Controller
@RequestMapping(value = "test")
public class TestController {

    @RequestMapping(value = "test")
    public String test(){
        return "test";
    }
}
