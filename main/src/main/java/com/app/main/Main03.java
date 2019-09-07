package com.app.main;


import com.app.service.ControlAppService;

public class Main03 {

    public static void main(String[] args) {
        final String appName = "App03 v1.07 27.04.2019 _K.Szot";
        System.out.println(appName);
        ControlAppService ca = new ControlAppService();
        ca.runApp();
    }
}

