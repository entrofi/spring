

package net.entrofi.commons.util.event;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EventHelper implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventHelper.class);

    private static EventHelper instance = null;

    private EventBus eventBus = new EventBus();

    private Set<Object> registeredListeners = new HashSet<>();

    public EventHelper() {
        instance = this;
    }


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        List<String> methodNames = getSubscribeMethodNames(bean.getClass());
        if (!methodNames.isEmpty()) {
            LOGGER.info("Registering bean {} with subscribe methods {}", beanName, String.join(", ", methodNames));
            registerEventListener(bean);
        } else {
            LOGGER.debug("Bean {} has no subscribe methods", beanName);
        }
        return bean;
    }

    @PreDestroy
    public void destroy() {
        registeredListeners.forEach(EventHelper::unregisterEventListener);
    }

    public static void publishEvent(BaseEvent event) {
        instance.eventBus.post(event);
    }

    public static void registerEventListener(Object listener) {
        if (instance.registeredListeners.add(listener)) {
            instance.eventBus.register(listener);
            LOGGER.debug("Registered event listener {}", listener);
        }
    }

    public static void unregisterEventListener(Object listener) {
        if (instance.registeredListeners.remove(listener)) {
            instance.eventBus.unregister(listener);
            LOGGER.debug("Unregistered event listener {}", listener);
        }
    }

    private List<String> getSubscribeMethodNames(Class<?> clazz) {
        List<String> result = new ArrayList<>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getAnnotationsByType(Subscribe.class).length > 0) {
                result.add(method.getName());
            }
        }
        return result;
    }

    @Subscribe
    public void onDeadEvent(DeadEvent deadEvent) {
        LOGGER.warn("No listeners found for event {} published by {}", deadEvent.getEvent(), deadEvent.getSource());
    }

}
