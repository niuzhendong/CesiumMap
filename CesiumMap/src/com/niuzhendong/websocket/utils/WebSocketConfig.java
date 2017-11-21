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
import com.niuzhendong.websocket.controller.IMWebSocketHandler;
import com.niuzhendong.websocket.service.WebSocketHandtor;


/** 
 * @author niu_zhendong
 * @date 2017年11月12日
 * @desc TODO 
 */  
@Configuration  
@EnableWebMvc  
@EnableWebSocket  
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Autowired  
    private WebSocketHandtor handshakeInterceptor;  
    /** 
     * ע��֧�ֵ�websocket ���� 
     */  
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry reg) {
		// TODO Auto-generated method stub
		reg.addHandler(gpsWebSocketHandler(),"/gpswebsocket").addInterceptors(handshakeInterceptor);
		reg.addHandler(gpsWebSocketHandler(),"/socketjs/gpswebsocket").addInterceptors(handshakeInterceptor).withSockJS();
		

		reg.addHandler(imWebSocketHandler(),"/imwebsocket").addInterceptors(handshakeInterceptor);
		reg.addHandler(imWebSocketHandler(),"/socketjs/imwebsocket").addInterceptors(handshakeInterceptor).withSockJS();
	}
	
	@Bean  
    public WebSocketHandler gpsWebSocketHandler() {  
          return new GpsWebSocketHandler();  
	}
    
	@Bean
    public WebSocketHandler imWebSocketHandler() {  
          return new IMWebSocketHandler();  
	}

}
