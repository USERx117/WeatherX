package org.fhtw.weatherX.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileController {

    private String lon;
    private String lat;
    private String updateTimer;
    private String lang;
    private String units;
    private String key;

    public fileController(String lon, String lat, String updateTimer, String language, String units) {
        this.lon = lon;
        this.lat = lat;
        this.updateTimer = updateTimer;
        this.lang = lang;
        this.units = units;
        this.key = key;
    }

    public void readConfig() throws FileNotFoundException {
        File conf = new File("conf");
        Scanner confScanner = new Scanner(conf);
        while(confScanner.hasNextLine()){
            String data = confScanner.nextLine();
            if(getFirstChars(data,3)=="lon"){
                this.lon = data.substring(4);
            }
            System.out.println(data);
            System.out.println(this.lon);
        }
    }

    public String getFirstChars(String input, int numberOfChars){
        if (input.length() > numberOfChars)
        {
            return input.substring(0, numberOfChars);
        } else {
            return input;
        }
    }


}
