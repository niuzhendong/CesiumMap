package com.niuzhendong.im.service;

import java.util.List;
import java.util.Map;

public interface IImService {
	public List<Map<String,String>> getFriends(Map<String,String> param);
	public List<Map<String,String>> getGroup(Map<String,String> param);
	public List<Map<String,String>> getOrganList(Map<String,String> param);
	public List<Map<String, String>> getUser(Map<String, String> param);
	public List<Map<String, String>> getFriendUsers(Map<String, String> param);
}
