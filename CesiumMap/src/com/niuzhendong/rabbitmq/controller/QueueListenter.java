package com.niuzhendong.rabbitmq.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSON;
import com.niuzhendong.gps.service.IGpsService;
import com.niuzhendong.websocket.controller.GpsWebSocketHandler;

@Component
public class QueueListenter implements MessageListener {
	
	@Resource
	private IGpsService gpsService;
	
	private GpsWebSocketHandler hander = new GpsWebSocketHandler();
	
	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		try{
            System.out.println(msg.toString());
            String message =new String(msg.getBody(),"UTF-8");
            Map<String,Object> m = JSON.parseObject(message);
            TextMessage webmsg = new TextMessage(msg.getBody());
            hander.sendMessage(webmsg);
            gpsService.saveGpsData(m);
            System.out.println(m.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
	}
}
