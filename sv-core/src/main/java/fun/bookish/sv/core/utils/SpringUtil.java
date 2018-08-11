package fun.bookish.sv.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * spring工具类
 */
public class SpringUtil {

    private SpringUtil(){}

    private static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /***
     * 根据一个bean的id获取容器中相应的bean
     */
    public static Object getBean(String beanId) throws BeansException {
        if (applicationContext.containsBean(beanId)) {
            return applicationContext.getBean(beanId);
        }
        return null;
    }

    /***
     * 根据一个bean的类型获取容器中相应的bean
     */
    public static <T> T getBeanByClass(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 根据类型获取bean集合
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> getBeansByType(Class<T> type){
        return new ArrayList<>(applicationContext.getBeansOfType(type).values());
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static ApplicationContext getApplicationContext() {
        return SpringUtil.applicationContext;
    }

}
