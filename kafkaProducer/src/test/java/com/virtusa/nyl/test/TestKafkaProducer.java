package com.virtusa.nyl.test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.virtusa.nyl.test.Listener;;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestKafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private Listener listener;

    @Test
    public void contextLoads() throws InterruptedException {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("topic2", "ABC");

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("success " + result.getProducerRecord().value());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println(ex);
                System.out.println("failed");
            }
        });
        System.out.println(Thread.currentThread().getId());
        assertThat(this.listener.countDownLatch1.await(60, TimeUnit.SECONDS)).isTrue();

    }
}
