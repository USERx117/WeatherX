package org.fhtw.weatherX.client;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.fhtw.weatherX.model.WeatherEntity;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;


@Controller
public class OpenWeatherClient {
    @Value("${openweather_api_key}")
    private String apiKey;
    @Value("${openweather_api_hostname}")
    private String hostname;
    @Value("${openweather_latitude}")
    private String latitude;
    @Value("${openweather_longitude}")
    private String longitude;
    @Value("${openweather_units}")
    private String units;
    @Value("${openweather_updateInterval}")
    private long interval;

    // Should be Trigger for Connect Button
    @EventListener(ApplicationReadyEvent.class)
    //ToDO this is the point where the connect button event should be called
    public void runAfterStartup()  {
        connect(latitude,longitude);
    }

    public void connect(String lat, String lon){

        StringBuilder header=new StringBuilder();
        header.append("GET /data/2.5/weather?lat=");
        header.append(lat);
        header.append("&lon=");
        header.append(lon);
        header.append("&appid=");
        header.append(apiKey);
        header.append("&units=");
        header.append(units);
        header.append(" HTTP/1.1\r\n");
        header.append("Connection: close\r\n");
        header.append("Host: ");
        header.append(this.hostname+"\r\n");
        header.append("\r\n");
        System.out.println("header from connect: ");
        System.out.println(header.toString());

        try{
            while(true){
                startConnection(header.toString());
                TimeUnit.MINUTES.sleep(interval);
            }
        }catch(IOException|InterruptedException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    private String startConnection(String header) throws IOException {

        try (final Socket socket = new Socket(hostname,80)) {
            final String request = header;

            final OutputStream outputStream = socket.getOutputStream();
            outputStream.write(request.getBytes(StandardCharsets.UTF_8));

            final InputStream inputStream = socket.getInputStream();
            final String response = readAsString(inputStream);
            //System.out.println(response);
            outputStream.close();
            inputStream.close();
            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
            //ToDo Weather Entity Object has all information to display on main windows after connect button is pressed
            WeatherEntity weatherEntity = new WeatherEntity(
                    jsonObject.getJSONObject("main").get("temp").toString(),
                    jsonObject.get("name").toString(),
                    jsonObject.getJSONObject("main").get("pressure").toString(),
                    jsonObject.getJSONObject("main").get("temp_min").toString(),
                    jsonObject.getJSONObject("main").get("temp_max").toString()
                    );
            System.out.println(weatherEntity.toString());
            return response;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private static String readAsString(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toString(StandardCharsets.UTF_8.name());
    }
}
