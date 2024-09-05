package org.spring.rank.www.springframworkrank.test03.spring;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TaoShuaiApplicationContext {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<String,BeanDefinition>();
    private Map<String,Object> singletonPool = new HashMap<String,Object>();

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
            return singletonPool.get(beanName);
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
