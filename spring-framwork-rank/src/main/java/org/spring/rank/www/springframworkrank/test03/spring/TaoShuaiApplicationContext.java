package org.spring.rank.www.springframworkrank.test03.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaoShuaiApplicationContext {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<String,BeanDefinition>();
    private Map<String,Object> singletonPool = new HashMap<String,Object>();
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    //配置类
    private Class configClass;

    //有参构造
    public TaoShuaiApplicationContext(Class configClass) {
        this.configClass = configClass;
        //扫描
        scan(configClass);

        //找出所有单例bean,放进单例池
        beanDefinitionMap.entrySet().forEach( entry ->{

            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();

            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singletonPool.put(beanName,bean);
            }


        });
    }

    //创建Bean
    private Object createBean(String beanName,BeanDefinition beanDefinition){
        Class<?> aClass = beanDefinition.getBeanClass();
        Object object = null;
        try {
            object = aClass.getConstructor().newInstance();

            //给属性赋值，即依赖注入
            for (Field field : aClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)){
                    field.setAccessible(true);
                    field.set(object,getBean(field.getName()));
                }
            }

            //初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                beanPostProcessor.postProcessBeforeInitialization(object,beanName);
            }
            //初始化
            if (object instanceof InitializingBean){
                ((InitializingBean) object).afterPropertiesSet();
            }
            //初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                beanPostProcessor.postProcessAfterInitialization(object,beanName);
            }

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return object;

    }

    //获取bean
    public Object getBean(String beanName) {

        //判断bean是否存在
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException("Bean不存在");
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        //判断是原型还是单例
        if (beanDefinition.getScope().equals("singleton")) {
            //单例
            Object singletonBean = singletonPool.get(beanName);
            if (singletonBean == null){
                singletonBean = createBean(beanName, beanDefinition);
                singletonPool.put(beanName,singletonBean);
            }
            return singletonBean;
        }else {
            //原型
            return createBean(beanName,beanDefinition);
        }

    }




    private void scan(Class configClass) {
        //通过传进来的配置类获取扫描路径
        if (configClass.isAnnotationPresent(ComponentScan.class)) {

            //1.先拿到扫描路径
            ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = annotation.value();
            path = path.replace(".", "/");

            //2.通过类加载器找到target目录下需要扫描的路径的已经编译的.class文件
            ClassLoader classLoader = TaoShuaiApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            //判断拿到的文件对象是否是一个目录
            try {
                if (file.isDirectory()) {
                    //遍历目录
                    for (File f : file.listFiles()) {
                        String absolutePath = f.getAbsolutePath();
                        absolutePath = absolutePath.substring(absolutePath.indexOf("org"), absolutePath.indexOf(".class"));
                        absolutePath = absolutePath.replace("\\", ".");

                        Class<?> aClass = classLoader.loadClass(absolutePath);

                        if (aClass.isAnnotationPresent(Component.class)) {

                            //判断当前的类是否实现了BeanPostProcessor接口
                            if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                                BeanPostProcessor instance =(BeanPostProcessor) aClass.getConstructor().newInstance();
                                beanPostProcessors.add(instance);
                            }

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setBeanClass(aClass);

                            //判断是单例还是原型
                            if (aClass.isAnnotationPresent(Scope.class)){
                                String value = aClass.getAnnotation(Scope.class).value();
                                beanDefinition.setScope(value);
                            }else {
                                //单例
                                beanDefinition.setScope("singleton");

                            }
                            String beanName = aClass.getAnnotation(Component.class).value();
                            //如果没有在注解中指定类的名字，则由spring自动生成一个
                            if ("".equals(beanName)){
                                beanName = Introspector.decapitalize(aClass.getSimpleName());
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }
                    }

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
