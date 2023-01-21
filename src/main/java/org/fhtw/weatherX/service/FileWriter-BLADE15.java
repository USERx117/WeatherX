package org.fhtw.weatherX.service;

public class FileWriter {

    public void writeToProperties() {

        FileReader reader = null;
        FileWriter writer = null;
        File file = new File("application.properties");

        try {
            reader = new FileReader(file);
            writer = new FileWriter(file);
            Properties p = new Properties();

            p.load(reader);
            p.setProperty("hostname","dev.com");
            p.store(writer,"write a file");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
