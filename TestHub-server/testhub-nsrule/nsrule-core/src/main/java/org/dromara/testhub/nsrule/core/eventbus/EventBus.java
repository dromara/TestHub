package org.dromara.testhub.nsrule.core.eventbus;

import java.util.List;
import java.util.concurrent.Executor;


public class EventBus {

    private ObserverRegistry registry = new ObserverRegistry();

    private Executor executor;

    public EventBus() {

    }

    public EventBus(Executor executor) {
        this.executor = executor;
    }

    /**
     * 注册观察者
     */
    public void register(Object observer) {
        registry.register(observer);
    }

    /**
     * 发布者-发送消息
     */
    public void post(EventType event, Object message) {
        List<ObserverAction> observerActions = registry.getMatchedObserverActions(event);
        for (ObserverAction observerAction : observerActions) {
            if (executor == null) {
                observerAction.execute(message);
            } else {
                executor.execute(() -> {
                    observerAction.execute(message);
                });
            }
        }
    }
}
