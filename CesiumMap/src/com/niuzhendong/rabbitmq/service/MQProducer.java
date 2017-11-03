package com.niuzhendong.rabbitmq.service;

public interface MQProducer {
	public void sendDataToQueue(String queueKey, Object object);
}
