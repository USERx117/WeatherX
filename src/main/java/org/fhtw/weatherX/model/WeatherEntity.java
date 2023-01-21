package org.fhtw.weatherX.model;

public class WeatherEntity {

    private String temperature;
    private String location;
    private String pressure;
    private String temp_min;
    private String temp_max;

    public WeatherEntity(String temperature, String location, String pressure, String temp_min, String temp_max) {
        this.temperature = temperature;
        this.location = location;
        this.pressure = pressure;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "temperature='" + temperature + '\'' +
                ", location='" + location + '\'' +
                ", pressure='" + pressure + '\'' +
                ", temp_min='" + temp_min + '\'' +
                ", temp_max='" + temp_max + '\'' +
                '}';
    }
}
