package com.niuzhendong.redis.mapper.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.niuzhendong.redis.mapper.IRedisDao;

public class RedisMapper implements IRedisDao {

	@Autowired
    private StringRedisTemplate redisTemplate;
	
	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;
	
	@Resource(name="redisTemplate")
	private HashOperations<String, String, String> hashOps;
	
	@Resource(name="redisTemplate")
	private SetOperations<String, String> setOps;
	
	@Override
	public String saveList(Map param) {
		// TODO Auto-generated method stub
		Long res = listOps.leftPush(param.get("id").toString(), param.get("name").toString());
		return res.toString();
	}

	@Override
	public String saveHash(Map param) {
		// TODO Auto-generated method stub
		hashOps.put(param.get("db").toString(),param.get("id").toString(), param.get("name").toString());
		return "2";
	}

	@Override
	public String saveValue(Map param) {
		// TODO Auto-generated method stub
		Long res = setOps.add(param.get("id").toString(), param.get("name").toString());
		return res.toString();
	}

	@Override
	public String saveSet(Map param) {
		// TODO Auto-generated method stub
		Long res = setOps.add(param.get("id").toString(), param.get("name").toString());
		return res.toString();
	}

	@Override
	public String savezSet(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getList(Map param) {
		// TODO Auto-generated method stub
		return listOps.rightPop(param.get("id").toString());
	}

	@Override
	public String getHash(Map param) {
		// TODO Auto-generated method stub
		return null;
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
