package com.example.linuxdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

public class TestStream {

    private static final Logger logger = LoggerFactory.getLogger(TestStream.class);

    public static void runCmdForResult(String cmd) {
        CmdResult cmdResult = null;
        try {
            String[] command = {"/bin/sh", "-c", cmd};
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            CompletableFuture<String> inputReadFuture = CompletableFuture.supplyAsync(() -> {
                StringBuilder info = new StringBuilder();
                try {
                    char[] chars = new char[1024];
                    int pos;
                    while ((pos = inputReader.read(chars)) != -1) {
                        info.append(chars, 0, pos);
                    }
                    inputReader.close();
                } catch (IOException e) {
                    logger.error("[bm-common]: 读取执行信息失败");
                }
                return info.toString();
            });

            CompletableFuture<String> errorReadFuture = CompletableFuture.supplyAsync(() -> {
                StringBuilder info = new StringBuilder();
                try {
                    char[] chars = new char[1024];
                    int pos;
                    while ((pos = errorReader.read(chars)) != -1) {
                        info.append(chars, 0, pos);
                    }
                    errorReader.close();
                } catch (IOException e) {
                    logger.error("[bm-common]: 读取错误信息失败", e);
                }
                return info.toString();
            });

            int res = process.waitFor();

            cmdResult = new CmdResult();
            cmdResult.setCode(res);
            cmdResult.setSucMessage(inputReadFuture.get());
            cmdResult.setErrMessage(errorReadFuture.get());

        } catch (Exception e) {
            logger.error("[bm-error]: 执行shell命令失败:", e);
        }
        System.out.println(cmdResult);
    }
}
