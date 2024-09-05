package org.spring.rank.www.springframworkrank.test03.spring;

/**
 * Bean的定义
 */
public class BeanDefinition {

    private String beanName;
    private Class<?> beanClass;
    //单例还是原型
    private String scope;
    //是否是懒加载
    private boolean isLazy;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }
}
