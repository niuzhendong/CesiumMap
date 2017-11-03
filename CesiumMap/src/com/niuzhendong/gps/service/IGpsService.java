package com.niuzhendong.gps.service;

import java.util.List;
import java.util.Map;

import org.geotools.geojson.geom.GeometryJSON;

import com.vividsolutions.jts.geom.GeometryCollection;

public interface IGpsService {
	public Map<String,String> getUserById(Map<String, String> userId);
	public String getGeoData(Map<String,String> table);
	public int saveGpsData(Map<String,Object> table);
}
