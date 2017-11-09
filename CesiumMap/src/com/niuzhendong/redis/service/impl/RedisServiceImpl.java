package com.niuzhendong.redis.service.impl;

import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.niuzhendong.redis.mapper.IRedisDao;
import com.niuzhendong.redis.service.IRedisService;


@Service("RedisService")
public class RedisServiceImpl implements IRedisService {

	@Resource  
	private IRedisDao redisDao;

	@Override
	public String saveList(Map param) {
		// TODO Auto-generated method stub
		return redisDao.saveList(param);
	}

	@Override
	public String saveHash(Map param) {
		// TODO Auto-generated method stub
		return redisDao.saveHash(param);
	}

	@Override
	public String saveValue(Map param) {
		// TODO Auto-generated method stub
		return redisDao.saveValue(param);
	}

	@Override
	public String saveSet(Map param) {
		// TODO Auto-generated method stub
		return redisDao.saveSet(param);
	}

	@Override
	public String savezSet(Map param) {
		// TODO Auto-generated method stub
		return redisDao.savezSet(param);
	}

	@Override
	public String getList(Map param) {
		// TODO Auto-generated method stub
		return redisDao.getList(param);
	}

	@Override
	public String getHash(Map param) {
		// TODO Auto-generated method stub
		return redisDao.getHash(param);
	}

	@Override
	public String getValue(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSet(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getzSet(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delList(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delHash(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delValue(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delSet(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delzSet(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updList(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updHash(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updValue(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updSet(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updzSet(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

}
