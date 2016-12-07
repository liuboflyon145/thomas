package com.thomas;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
 
public class TestProducer {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {
        long events = Long.parseLong("5");
        Random rnd = new Random();

//        Properties props = new Properties();
//        props.put("metadata.broker.list'","broker1:9092");
//        props.put("bootstrap.servers", "localhost:4242");
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
//
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:4242");
        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.thomas.SimplePartitioner");
        props.put("request.required.acks", "1");
 
        ProducerConfig config = new ProducerConfig(props);
 
        Producer<String, String> producer = new Producer<String, String>(config);
 
        for (long nEvents = 0; nEvents < events; nEvents++) { 
               long runtime = new Date().getTime();  
               String ip = "192.168.2." + rnd.nextInt(255);
               String msg = runtime + ",www.example.com," + ip;
               KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits", ip, msg);
               producer.send(data);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        producer.close();
    }
}