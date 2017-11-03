package com.niuzhendong.gps.mapper;

import java.util.List;
import java.util.Map;

public interface IGpsDao {
	public Map<String,String> getUserById(Map<String, String> userId);
	public List<Map<String, Object>> getGeoData(Map<String,String> table);
	public int saveGpsData(Map<String,Object> table);
}
