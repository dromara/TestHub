package com.goddess.nsrule.core.eventbus;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


public class ObserverRegistry {

    private ConcurrentHashMap<EventType, CopyOnWriteArraySet<ObserverAction>> registry = new ConcurrentHashMap<>();

    /**
     * 注册
     */
    public void register(Object observer) {
        //遍历带有注解的方法，将事件和对应的多个处理方法，存储到map中
        Map<EventType, Collection<ObserverAction>> observerActions = findAllObserverActions(observer);

        //将获取到的单个观察者的可执行方法，放到如全局的map中，使用并发类
        for (Map.Entry<EventType, Collection<ObserverAction>> entry : observerActions.entrySet()) {
            EventType eventType = entry.getKey();
            Collection<ObserverAction> eventActions = entry.getValue();
            CopyOnWriteArraySet<ObserverAction> registeredEventActions = registry.get(eventType);
            if (registeredEventActions == null) {
                registry.putIfAbsent(eventType, new CopyOnWriteArraySet<>());
                registeredEventActions = registry.get(eventType);
            }
            registeredEventActions.addAll(eventActions);
        }
    }

    /**
     * 遍历带有注解的方法，将事件和对应的多个处理方法，存储到map中
     *
     * @param observer
     * @return
     */
    private Map<EventType, Collection<ObserverAction>> findAllObserverActions(Object observer) {
        Class<?> clazz = observer.getClass();
        List<Method> methodList = getAnnotateMethods(clazz);

        Map<EventType, Collection<ObserverAction>> observerActions = new HashMap<>();
        for (Method method : methodList) {
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if (!observerActions.containsKey(subscribe.event())) {
                observerActions.put(subscribe.event(), new ArrayList<>());
            }
            observerActions.get(subscribe.event()).add(new ObserverAction(observer, method));
        }
        return observerActions;
    }

    /**
     * 获取观察者中含有注解的方法
     *
     * @param clazz
     * @return
     */
    private List<Method> getAnnotateMethods(Class<?> clazz) {
        List<Method> annotateMethods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
//                Class<?>[] parameterTypes = method.getParameterTypes();
                annotateMethods.add(method);
            }
        }

        return annotateMethods;
    }

    /**
     * 根据事件获取合适的观察者方法
     *
     * @param event
     * @return
     */
    public List<ObserverAction> getMatchedObserverActions(EventType event) {
        List<ObserverAction> matchedObservers = new ArrayList<>();
        if (registry.get(event) != null) {
            matchedObservers.addAll(registry.get(event));
        }
        return matchedObservers;
    }

}
