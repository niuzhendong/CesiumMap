package com.niuzhendong.gps.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.geotools.geojson.geom.GeometryJSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.niuzhendong.gps.service.IGpsService;


@Controller  
@RequestMapping("/gps")
public class GpsController {
	@Resource
	private IGpsService gpsService;
	
	@RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        Map<String,String> userId = new HashMap<String,String>();
        
        userId.put("userId", request.getParameter("id"));  
        Map<String,String> user = this.gpsService.getUserById(userId);  
        model.addAttribute("user",user);  
        return "test";  
    }  
	
	@ResponseBody
	@RequestMapping("/getGeoData")  
    public JSONObject getGeoData(HttpServletRequest request){  
        String param = request.getParameter("table");
		Map<String,String> table = new HashMap<String,String>();
		table.put("table", param); 
		String GeoData = this.gpsService.getGeoData(table);
		JSONObject jsonObject = (JSONObject) JSONObject.parse(GeoData);
		
        //model.addAttribute("user",GeoData);  
        return jsonObject;  
    }
}
