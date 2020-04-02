package org.linn.user.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * created by linn  20/4/2 23:31
 **/
@Service
@Slf4j
public class StreamConsumer {

    @StreamListener(Sink.INPUT)
    public void receive(String message) {
        log.info("收到了消息: {} ", message);
    }

}
