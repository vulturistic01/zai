# Zai Weather Service – Code Challenge

This is a Spring Boot application built as part of the Zai coding challenge.

---

## 🌤️ What It Does

- Exposes an HTTP endpoint to report **Melbourne weather**
- Primary source: **WeatherStack**
- Failover: **OpenWeatherMap**
- Unified response: temperature (°C) and wind speed
- Includes:
  - 3-second caching
  - Stale fallback if all APIs fail
  - Unit and controller tests

---

## 🛠️ How to Run

1. Replace API keys in:
   - `WeatherStackService.java`
   - `OpenWeatherMapService.java`

2. Build and run:

```bash
./mvnw spring-boot:run
Call the service:
curl "http://localhost:8080/v1/weather?city=melbourne"

✅ Test It

mvn test
Includes:

Controller test with @WebMvcTest

Service failover test with mocked providers
