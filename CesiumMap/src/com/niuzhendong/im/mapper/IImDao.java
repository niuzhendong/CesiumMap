package com.niuzhendong.im.mapper;

import java.util.List;
import java.util.Map;

public interface IImDao {
	public List<Map<String,String>> getFriends(Map<String,String> param);
	public List<Map<String,String>> getUser(Map<String,String> param);
	public List<Map<String,String>> getGroup(Map<String,String> param);
	public List<Map<String,String>> getOrganList(Map<String,String> param);
	public List<Map<String, String>> getFriendUsers(Map<String, String> param);
}
