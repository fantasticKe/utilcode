package org.fantasticmao.utilcode.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author maokeluo
 * @desc
 * @create 17-9-16
 */
@Controller
@RequestMapping(value = "test")
public class TestController {

    @RequestMapping(value = "test")
    public ModelAndView test(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("test");
        return mav;
    }
}
