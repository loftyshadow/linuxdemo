package com.example.linuxdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestLinux {

    private static final Logger logger = LoggerFactory.getLogger(TestLinux.class);

    public void task() {
        //执行该命令
        List<String> commands= new ArrayList<>();
        commands.add("cd /home/mysql_bak");
        commands.add("cat bak.log");
        List<String> list = executeNewFlow(commands);
        logger.info("得到备份执行结果数据:" + list);
    }


    public static List<String> executeNewFlow(List<String> commands) {
        List<String> rspList = new ArrayList<String>();
        Runtime run = Runtime.getRuntime();
        try {
            Process proc = run.exec("/bin/bash", null, null);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
            for (String line : commands) {
                logger.info("执行命令:" + line);
                out.println(line);
            }
            // 这个命令必须执行，否则in流不结束。
            out.println("exit");
            String rspLine = "";
            logger.info("返回结果:" + in.readLine());
            while ((rspLine = in.readLine()) != null) {
                System.out.println(rspLine);
                logger.info("返回结果:" + rspLine);
                rspList.add(rspLine);
            }
            proc.waitFor();
            in.close();
            out.close();
            proc.destroy();
        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        }
        return rspList;
    }
}
