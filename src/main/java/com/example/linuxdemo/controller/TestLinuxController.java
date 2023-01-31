package com.example.linuxdemo.controller;

import com.example.linuxdemo.utils.TestLinux;
import com.example.linuxdemo.utils.TestStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestLinuxController {

    private static final Logger logger = LoggerFactory.getLogger(TestLinuxController.class);

    @RequestMapping("/testLinux")
    public String testLinux(@RequestParam("cmd") String cmd) {
        System.out.println("cmd命令" + cmd);
        List<String> outputList = TestLinux.executeNewFlow(List.of(cmd));
        if (cmd.contains("cat")) {
            logger.debug("包含cat进入测试代码中");
            for (String str : outputList) {
                if (str.contains("Mounted media class")) {
                    System.out.println("Mounted media class对应" + str.substring(str.indexOf(":") + 1));
                } else if (str.contains("Mounted media type")) {
                    System.out.println("Mounted media type对应" + str.substring(str.indexOf(":") + 1));
                }
            }
        }
        return "index";
    }
    @RequestMapping("/testLinux2")
    public String testLinux2(@RequestParam("cmd") String cmd) {
        System.out.println("cmd命令" + cmd);
        TestStream.runCmdForResult(cmd);
        return "index";
    }
}
