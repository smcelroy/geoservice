package pkg.geo.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
 
@Controller
public class GeoServiceController {
 
	@RequestMapping("result")
	public ModelAndView addressRequest() {
		//https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyCjgkPSgTKPDyM_Li8a7GsWUnZZLFOsq7c
		String latLong = "";
		return new ModelAndView("result", "message", latLong);
	}
}