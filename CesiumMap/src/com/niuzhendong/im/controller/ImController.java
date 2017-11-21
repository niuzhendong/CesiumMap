package com.niuzhendong.im.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.niuzhendong.im.service.IImService;

@Controller  
@RequestMapping("/im")
public class ImController {
	@Resource
	private IImService imservice;
	
	@RequestMapping("/getFriends")
    public @ResponseBody JSONObject getFriends(HttpServletRequest request){  
        
        
        Map<String,String> param = new HashMap<String, String>();
        
        param.put("username", request.getParameter("username"));
		
		List<Map<String,String>> im_friends = this.imservice.getFriends(param);
		List<Map<String,String>> im_group = this.imservice.getGroup(param);
		List<Map<String,String>> im_mine = this.imservice.getUser(param);
		
		
		Map<String,Object> res = new HashMap<String, Object>();
		Map<String,Object> data = new HashMap<String, Object>();
		
		Map<String,String> mine = new HashMap<String,String>();
		List<Map<String,Object>> friends = new ArrayList<Map<String,Object>>();
		List<Map<String,String>> group = new ArrayList<Map<String,String>>();
		
		
		for(int i=0;i<im_friends.size();i++){
			Map<String,Object> n_friend = new HashMap<String, Object>();
			String groupString = im_friends.get(i).get("NAME");
			n_friend.put("groupname", groupString);
			n_friend.put("id", im_friends.get(i).get("GROUPID"));
			
			Map<String,String> fpara = new HashMap<String, String>();
			fpara.put("groupid", im_friends.get(i).get("GROUPID"));
			List<Map<String,String>> im_friend_list = this.imservice.getFriendUsers(fpara);
			List<Map<String,String>> friend_list = new ArrayList<Map<String,String>>();
			
			//在线个数，默认全部在线，后续加入缓存机制，实时获取
			n_friend.put("online", im_friend_list.size());
			
			for(int j=0;j<im_friend_list.size();j++){
				Map<String,String> friend = new HashMap<String,String>();
				friend.put("username", im_friend_list.get(j).get("NICKNAME"));
				friend.put("id", im_friend_list.get(j).get("USERNAME"));
				friend.put("sign", im_friend_list.get(j).get("DESCRIPTION"));
				friend.put("avatar", im_friend_list.get(j).get("ICON"));
				friend_list.add(friend);
			}
			n_friend.put("list",friend_list);
			friends.add(n_friend);
		}
		for(int i=0;i<im_group.size();i++){
			Map<String,String> n_group = new HashMap<String, String>();
			n_group.put("groupname", im_group.get(i).get("NAME"));
			n_group.put("id", im_group.get(i).get("GROUPID"));
			n_group.put("avatar", im_group.get(i).get("ICON"));
			group.add(n_group);
		}
		for(int i=0;i<im_mine.size();i++){
			mine.put("username", im_mine.get(i).get("NICKNAME"));
			mine.put("id", im_mine.get(i).get("USERNAME"));
			mine.put("status", "online");
			mine.put("sign", im_mine.get(i).get("DESCRIPTION"));
			mine.put("avatar", im_mine.get(i).get("ICON"));
		}
		data.put("mine", mine);
		data.put("friend", friends);
		data.put("group", group);
		res.put("code", "");
		res.put("msg", "");
		res.put("data", data);
		
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(res);
		
		return jsonObject;
    }
	
	@RequestMapping("/login")  
    public ModelAndView toIndex(HttpServletRequest request,ModelAndView model){
		
		Map<String,String> param = new HashMap<String,String>();
		param.put("username", request.getParameter("username"));
		param.put("password", request.getParameter("password"));
		try{
			List<Map<String, String>> user = this.imservice.getUser(param);
	        if(param.get("password").equals(user.get(0).get("PASSWORD"))){
	        	model = new ModelAndView("./index");
	        	request.getSession().setAttribute("user", user.get(0).get("USERNAME"));
	        	request.getSession().setAttribute("pasd", user.get(0).get("PASSWORD"));
	        }else{
	        	model = new ModelAndView("login/login");
	        }
	        return model;  
		}catch(Exception e){
			e.printStackTrace();
			return new ModelAndView("./login/login");
		}
    }
}
