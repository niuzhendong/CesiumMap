package com.niuzhendong.redis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
        
        userId.put("id", "111");
        userId.put("name", "111");
        
        return this.redisService.getList(userId);
    }  
}
