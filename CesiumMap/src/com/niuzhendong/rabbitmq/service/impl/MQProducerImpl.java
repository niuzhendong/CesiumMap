package com.niuzhendong.rabbitmq.service.impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niuzhendong.rabbitmq.service.MQProducer;

@Service
public class MQProducerImpl implements MQProducer {

	@Autowired
    private AmqpTemplate amqpTemplate;
	
	private final static Logger LOGGER = Logger.getLogger(MQProducerImpl.class);
	
	@Override
	public void sendDataToQueue(String queueKey, Object object) {
		// TODO Auto-generated method stub
		try {
            amqpTemplate.convertAndSend(queueKey, object);
        } catch (Exception e) {
            LOGGER.error(e);
        }
	}

}
