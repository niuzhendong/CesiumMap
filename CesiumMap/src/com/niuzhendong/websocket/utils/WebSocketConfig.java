package com.niuzhendong.websocket.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.niuzhendong.websocket.controller.GpsWebSocketHandler;
import com.niuzhendong.websocket.service.WebSocketHandtor;


/** 
 * websocket ������, ����websocket��ʽʵ�ֵ�ͨѶ������ֱ��ʹ��tcpЭ�飬���ڵײ�Э�飬spring��Ӧ�ò� 
 * ���ø߲��Э����г�����ͨѶ��stomp 
 * @author tomZ 
 * @date 2016��11��2�� 
 * @desc TODO 
 */  
@Configuration  
@EnableWebMvc  
@EnableWebSocket  
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	@Autowired  
    private GpsWebSocketHandler  handler;  
    @Autowired  
    private WebSocketHandtor handshakeInterceptor;  
    /** 
     * ע��֧�ֵ�websocket ���� 
     */  
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry reg) {
		// TODO Auto-generated method stub
		reg.addHandler(handler,"/gpswebsocket").addInterceptors(handshakeInterceptor);
	}
	
	@Bean  
    public WebSocketHandler systemWebSocketHandler() {  
          return new GpsWebSocketHandler();  
   }  

}
