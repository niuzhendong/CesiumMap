package com.niuzhendong.websocket.service;

import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;


/** 
 * �������ӣ����������� 
 * @author tomZ 
 * @date 2016��11��2�� 
 * @desc TODO 
 */  
@Component
public class WebSocketHandtor implements HandshakeInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketHandtor.class);
	
	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		// TODO Auto-generated method stub
		logger.info("after hand shake {}", this.getClass().getName());  
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest req, ServerHttpResponse arg1, WebSocketHandler arg2,
			Map<String, Object> arg3) throws Exception {
		// TODO Auto-generated method stub
		logger.info("before hand shake {}", this.getClass().getName());  
        /**  
        * This is a bug,bug fix:The extension [x-webkit-deflate-frame] is not supported  
        */    
       if(req.getHeaders().containsKey("Sec-WebSocket-Extensions")) {    
           req.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
       }
       return true;
	}

}
