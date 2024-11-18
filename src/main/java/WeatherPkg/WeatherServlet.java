package WeatherPkg;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WeatherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String apiKey = "your-api-key";
        String city = request.getParameter("city"); 
		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
		
		try {
			URI uri = URI.create(apiUrl);
	        URL url = uri.toURL();
	        
	        // API Integration
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // setRequestMethod("GET") specifies the HTTP method as "GET" (GET method for API) 
            
            
            // Read data from network
            InputStream inputStream = connection.getInputStream(); // Opens a stream to read the server's response
            InputStreamReader reader = new InputStreamReader(inputStream); // Wraps the InputStream to convert byte data into string
            StringBuilder responseContent = new StringBuilder(); //  used to store the API's response content as string
            
            Scanner scanner = new Scanner(reader); // Scanner object is created to read the text from the InputStreamReader
            while (scanner.hasNext()) {
                responseContent.append(scanner.nextLine());
            }
            System.out.println(responseContent);
            scanner.close();
            
            
            // Parsing data into json to extract temperature, date, and humidity
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
            System.out.println(jsonObject);
            
            long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000; //Date & Time
            String date = new Date(dateTimestamp).toString();
            
            double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble(); //Temperature
            int temperatureCelsius = (int) (temperatureKelvin - 273.15);
           
            int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt(); //Humidity
            
            double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble(); //Wind Speed
            
            String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString(); //Weather Condition
            
            
            // Set the data as request attributes (for sending to the jsp page)
            request.setAttribute("date", date);
            request.setAttribute("city", city);
            request.setAttribute("temperature", temperatureCelsius);
            request.setAttribute("weatherCondition", weatherCondition); 
            request.setAttribute("humidity", humidity);    
            request.setAttribute("windSpeed", windSpeed);
            request.setAttribute("weatherData", responseContent.toString());
            
            
            
            connection.disconnect();
            
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Forward the request to the weather.jsp page for rendering
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
