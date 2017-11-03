package com.niuzhendong.cesium.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuzhendong.cesium.mapper.ICesiumDao;
import com.niuzhendong.cesium.service.ICesiumService;

@Service("CesiumService")
public class CesiumService implements ICesiumService {
	
	@Resource  
    private ICesiumDao cesiumDao; 
	
	@Override
	public Map<String, String> getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.cesiumDao.getUserById(userId);
	}

	@Override
	public List<Map<String, String>> getGeoData(Map<String, String> table) {
		// TODO Auto-generated method stub
		return this.cesiumDao.getGeoData(table);
	}

}
