package com.niuzhendong.redis.service;

import java.util.Map;

public interface IRedisService {
	public String saveList(Map param);
	public String saveHash(Map param);
	public String saveValue(Map param);
	public String saveSet(Map param);
	public String savezSet(Map param);

	public String getList(Map param);
	public String getHash(Map param);
	public String getValue(Map param);
	public String getSet(Map param);
	public String getzSet(Map param);
	
	public String delList(Map param);
	public String delHash(Map param);
	public String delValue(Map param);
	public String delSet(Map param);
	public String delzSet(Map param);
	
	public String updList(Map param);
	public String updHash(Map param);
	public String updValue(Map param);
	public String updSet(Map param);
	public String updzSet(Map param);
}
