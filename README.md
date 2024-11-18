# Weather Web App 🌦️

This is a simple weather web application built using Java, Servlets, JSP, HTML, CSS, and the OpenWeather API. The app allows users to search for weather information of a city, including temperature, humidity, wind speed, and weather condition. It utilizes the OpenWeather API to fetch real-time weather data.

![Weather App Screenshot1](images/ss1.png)
![Weather App Screenshot2](images/ss2.png)

## Features
- Search weather data by city name
- Displays:
  - Temperature (in Celsius)
  - Weather condition (Clear, Clouds, Rain, etc.)
  - Humidity
  - Wind Speed
  - Date and time of the data
- Dynamic weather icon based on the weather condition
- Responsive design using CSS
- Built with Java Servlets and JSP

## Technologies Used
- **Frontend**: HTML, CSS, JavaScript
- **Backend**: Java, Servlets, JSP
- **API**: OpenWeather API for weather data
- **Icons**: Font Awesome for search icon
- **Weather Icons**: Custom icons based on weather conditions

## Installation

### Prerequisites
- Java 8 or higher
- Apache Tomcat server (or any servlet container)
- OpenWeather API Key (you need to sign up for a free API key)

### Steps to Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/weather-web-app.git
   
2. **Setup API Key:**
- Sign up at OpenWeather API and get your free API key.
- Replace `your-api-key` in the WeatherServlet.java file with your actual API key.

3. **Deploy the project:**
- Use any servlet container (like Apache Tomcat) to deploy the project.
- Place the project in the webapps folder (for Tomcat) and run the server.

4. **Access the App:**
Open your browser and navigate to http://localhost:8080/WeatherWebApp/.

## How It Works

1. **User Input:**
The user enters a city name into the search bar on the index.html page.

2. **Fetching Data:**
On form submission, a POST request is sent to WeatherServlet.
The servlet makes a request to the OpenWeather API with the city name.

3. **Displaying Data:**
The servlet retrieves weather data (temperature, humidity, wind speed, etc.) and sends it to the index.jsp page.
The index.jsp page displays the data dynamically, along with a weather icon based on the weather condition.
