package com.niuzhendong.gps.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.Resource;

import org.geotools.geojson.geom.GeometryJSON;
import org.springframework.stereotype.Service;

import com.niuzhendong.gps.mapper.IGpsDao;
import com.niuzhendong.gps.service.IGpsService;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@Service("GpsService")
public class GpsService implements IGpsService {
	@Resource  
    private IGpsDao gpsDao;
	@Override
	public Map<String, String> getUserById(Map<String, String> userId) {
		// TODO Auto-generated method stub
		return this.gpsDao.getUserById(userId);
	}
	@Override
	public String getGeoData(Map<String, String> table) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> geodata = this.gpsDao.getGeoData(table);
		GeometryJSON geometryJSON = new GeometryJSON();
		String json = null;
		try {
			WKTReader reader = new WKTReader(); 
			List<Geometry> geolist = new ArrayList<Geometry>();
			for(int i = 0;i<geodata.size();i++){
				Geometry geometry = reader.read((String)geodata.get(i).get("shape"));
				geolist.add(geometry);
			}
			GeometryFactory geometryfactory = new GeometryFactory();
			
			Geometry[] desc = new Geometry[geolist.size()];
			GeometryCollection geometrycols = geometryfactory.createGeometryCollection(geolist.toArray(desc));
			
			StringWriter writer = new StringWriter();
			geometryJSON.write(geometrycols,writer);
			
			json = writer.toString();
			//Reader strReader = new StringReader(geometrycols.toText());
			//geometryJSON.readGeometryCollection(strReader);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	@Override
	public int saveGpsData(Map<String,Object> table) {
		// TODO Auto-generated method stub
		return this.gpsDao.saveGpsData(table);
	}

}
