package pkg.geo.controller
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
 
@Controller
public class GeoServiceController {
 
	String googleMapAPI = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	String apiKey = "&key=AIzaSyCjgkPSgTKPDyM_Li8a7GsWUnZZLFOsq7c";
	
	@RequestMapping("/test")
	public ModelAndView addressRequest() {
		//https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyCjgkPSgTKPDyM_Li8a7GsWUnZZLFOsq7c
		String latLong = "";
		return new ModelAndView("result", "message", latLong);
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public ModelAndView geoLocationRequest(
			@RequestParam("address") String address, 
			@RequestParam("distance") double distance ) {
		
		GeoLocation geoLocation = new GeoLocation();
		HttpURLConnection request;
		BufferedReader instream = null;
		String response = null;
		address = address.replace(' ', '+');
		System.out.println(googleMapAPI+address+apiKey);
		try{
			request = (HttpURLConnection)new URL(googleMapAPI+address+apiKey).openConnection();
			instream = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = instream.readLine()) != null) result.append(line);
			response = result.toString();
		}catch(IOException e){
			System.out.println("404 Google API Not Found");
		}finally{
			if (instream != null) try {instream.close();} catch (IOException e) {}
		}
		
		JSONObject json;
		double latitude = 0;
		double longitude = 0;
		try {
			json = new JSONObject(response);
			latitude = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
			longitude = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		System.out.println(latitude+","+longitude);
		geoLocation.setAddress(address);
		geoLocation.setDistance(distance);
		geoLocation.setCount(0);
		geoLocation.setLatitude(latitude);
		geoLocation.setLongitude(longitude);
		return new ModelAndView("result", "geoLocation", geoLocation);
	}
	
}