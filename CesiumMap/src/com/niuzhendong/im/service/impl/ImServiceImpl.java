package com.niuzhendong.im.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niuzhendong.im.mapper.IImDao;
import com.niuzhendong.im.service.IImService;

@Service
public class ImServiceImpl implements IImService {

	@Autowired
	private IImDao imdao;
	
	@Override
	public List<Map<String, String>> getFriends(Map<String, String> param) {
		// TODO Auto-generated method stub
		return imdao.getFriends(param);
	}

	@Override
	public List<Map<String, String>> getGroup(Map<String, String> param) {
		// TODO Auto-generated method stub
		return imdao.getGroup(param);
	}

	@Override
	public List<Map<String, String>> getOrganList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return imdao.getOrganList(param);
	}

	@Override
	public List<Map<String, String>> getUser(Map<String, String> param) {
		// TODO Auto-generated method stub
		return imdao.getUser(param);
	}

	@Override
	public List<Map<String, String>> getFriendUsers(Map<String, String> param) {
		// TODO Auto-generated method stub
		return imdao.getFriendUsers(param);
	}

}
