package com.niuzhendong.cesium.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niuzhendong.cesium.service.ICesiumService;
import com.vividsolutions.jts.geom.Geometry;

import org.apache.commons.collections.map.HashedMap;
import org.geotools.geojson.*;
import org.geotools.geojson.geom.GeometryJSON;

@Controller  
@RequestMapping("/user")
public class CesiumController {
	@Resource
	private ICesiumService cesiumService;
	
	@RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        Map<String,String> user = this.cesiumService.getUserById(userId);  
        model.addAttribute("user",user);  
        return "test";  
    }
	
	@RequestMapping("/getGeoData")  
    public String getGeoData(HttpServletRequest request,Model model){  
        //String table = request.getParameter("table");
		Map<String,String> table = new HashMap<String,String>();
		table.put("table", "FQ_ZY_PG"); 
		List<Map<String,String>> GeoData = this.cesiumService.getGeoData(table);  
        model.addAttribute("user",GeoData);  
        return "test";  
    }
	
	@RequestMapping("/toText")  
    public String toText(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id")); 
        
        GeometryJSON geometryJSON = new GeometryJSON();
        Geometry user = null;
        
        File file = new File(request.getSession().getServletContext().getRealPath("") +"/asserts/json/CHN.json");
        InputStream in;
		try {
			in = new FileInputStream(file);
			Reader inn = new InputStreamReader(in);
			user = geometryJSON.read(inn);
			System.out.println(user.toText());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        model.addAttribute("user",user);  
        return "test";  
    }  
}
