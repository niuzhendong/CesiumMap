package com.niuzhendong.cesium.service;

import java.util.List;
import java.util.Map;

public interface ICesiumService {
	public Map<String,String> getUserById(int userId);

	public List<Map<String, String>> getGeoData(Map<String, String> table);
}
