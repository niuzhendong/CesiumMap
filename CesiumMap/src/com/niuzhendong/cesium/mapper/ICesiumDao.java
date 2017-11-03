package com.niuzhendong.cesium.mapper;

import java.util.List;
import java.util.Map;

public interface ICesiumDao {
	public Map<String,String> getUserById(int userId);
	public List<Map<String,String>> getGeoData(Map<String,String> table);
}
