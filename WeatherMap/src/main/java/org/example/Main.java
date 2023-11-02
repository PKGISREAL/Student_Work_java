package org.example;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> cities = new ArrayList<>();
        List<Weather> weathers = new ArrayList<>();

        cities.add("Moscow");
        cities.add("Yakutsk");
        cities.add("Vanavara");
        cities.add("Baku");
        cities.add("Ust'-Kut");
        cities.add("Anapa");
        cities.add("Ekaterinburg");
        cities.add("Novosibirsk");
        cities.add("Ulan-Ude");
        cities.add("Irkutsk");

        for (String city : cities) {
            try {
                String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=2818af25d7e2e11c41482955b2398388";

                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();


                Gson gson = new Gson();
                Weather weather = gson.fromJson(response.toString(), Weather.class);
                weathers.add(weather);

            } catch (IOException e) {
                System.out.println("Не удалось получить погоду для города " + city);
            }
        }

        Collections.sort(weathers);
        for (Weather weather : weathers) {
            System.out.println(weather.name + ": " + weather.getTemperature() + "°C");
        }

    }

    public static class Weather implements Comparable<Weather>{
        private String name;
        private Temp main;

        public Weather(String name, Temp main) {
            this.name = name;
            this.main = main;
        }

        public int getTemperature() {
            return (int) Math.round(main.temp - 273);
        }

        @Override
        public int compareTo(Weather w) {
            return w.getTemperature() - this.getTemperature();
        }
    }

    public static class Temp {
        private double temp;

        public Temp(double t) {
            this.temp = t;
        }
    }
}