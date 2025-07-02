
		package com.weather.app;

		import java.io.BufferedReader;
		import java.io.InputStreamReader;
		import java.net.HttpURLConnection;
		import java.net.URL;
		import java.util.Scanner;
		import org.json.JSONObject;

		public class WeatherApp {

		    public static void main(String[] args) {
		        Scanner scanner = new Scanner(System.in);

		        System.out.print("Enter city name: ");
		        String city = scanner.nextLine();

		        String apiKey = "YOUR_API_KEY"; // Replace with your OpenWeatherMap API Key
		        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

		        try {
		            URL url = new URL(urlString);
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		            conn.setRequestMethod("GET");

		            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            String line;
		            StringBuilder responseContent = new StringBuilder();

		            while ((line = reader.readLine()) != null) {
		                responseContent.append(line);
		            }
		            reader.close();

		            parseWeather(responseContent.toString());

		        } catch (Exception e) {
		            System.out.println("Error: " + e.getMessage());
		        }

		        scanner.close();
		    }

		    private static void parseWeather(String responseBody) {
		        JSONObject obj = new JSONObject(responseBody);

		        String cityName = obj.getString("name");
		        JSONObject main = obj.getJSONObject("main");
		        double temperature = main.getDouble("temp");
		        int humidity = main.getInt("humidity");
		        JSONObject weather = obj.getJSONArray("weather").getJSONObject(0);
		        String description = weather.getString("description");

		        System.out.println("\nWeather Information:");
		        System.out.println("City: " + cityName);
		        System.out.println("Temperature: " + temperature + "°C");
		        System.out.println("Humidity: " + humidity + "%");
		        System.out.println("Description: " + description);
		    }
		

		

	}


