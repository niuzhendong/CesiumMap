package com.niuzhendong.redis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niuzhendong.redis.service.IRedisService;

@Controller  
@RequestMapping("/redis")
public class RedisController {
	@Resource
	private IRedisService redisService;
	
	@RequestMapping("/saveUser")  
    public @ResponseBody String saveUser(HttpServletRequest request,Model model){  
        Map<String,String> userId = new HashMap<String,String>();
        userId.put("db", "cesium:user");
        userId.put("id", "admin");
        userId.put("name", "admin1");
        return this.redisService.saveHash(userId);
    }
	
	@RequestMapping(value="/getUser", method = RequestMethod.POST)
    public @ResponseBody String getUser(@RequestBody Map param){  
        Map<String,String> userId = new HashMap<String,String>();
        userId.put("db", "cesium:user");
        userId.put("id", param.get("username").toString());
        if(param.get("password")!=null){
        	if(param.get("password").equals(this.redisService.getHash(userId))){
            	return "1";
            }
        }
        return "0";
    }
}
