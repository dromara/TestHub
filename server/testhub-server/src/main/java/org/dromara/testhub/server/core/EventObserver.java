package org.dromara.testhub.server.core;


import com.goddess.nsrule.core.eventbus.EventMessage;
import com.goddess.nsrule.core.eventbus.EventType;
import com.goddess.nsrule.core.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventObserver {
    private static String TOPIC_URL = "/topic/getRunRuleInfo";
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Subscribe(event = EventType.visit_flow)
    public void ding(EventMessage message) {
//        log.info("=========/topic/visit_flow");
//        simpMessagingTemplate.convertAndSend(TOPIC_URL, message.getData().toString());
    }

    @Subscribe(event = EventType.exit_exec)
    public void exit_exec(EventMessage message) {
//        simpMessagingTemplate.convertAndSend(TOPIC_URL, message.getData().toString());
//        simpMessagingTemplate.convertAndSendToUser("1234", TOPIC_URL, "visit_exec=="+message.getData().toString());
//        log.info("=========/topic/visit_exec"+message.getData().toString());
//        simpMessagingTemplate.convertAndSend( TOPIC_URL, "visit_exec=="+message.getData().toString());
    }

}
