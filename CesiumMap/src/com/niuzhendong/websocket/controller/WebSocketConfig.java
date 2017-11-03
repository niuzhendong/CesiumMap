package com.niuzhendong.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/** 
 * websocket 配置类, 采用websocket形式实现的通讯类似于直接使用tcp协议，属于底层协议，spring中应用层 
 * 采用高层的协议进行长连接通讯，stomp 
 * @author tomZ 
 * @date 2016年11月2日 
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
     * 注册支持的websocket 连接 
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
