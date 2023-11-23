package com.jie.spring.beans.factory;

import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.support.DefaultListableBeaFactory;
import com.jie.spring.test.HelloService;
import com.jie.spring.test.User;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author jie
 * @date 2023/11/23 23:19
 */
public class beanDefinitionAndBeanInstantiationTest {

    @Test
    public void testBeanFactory_succeed_paramStructure() {
        DefaultListableBeaFactory factory = new DefaultListableBeaFactory();
        BeanDefinition beanDefinition = new BeanDefinition(User.class);
        String beanName = "user";
        factory.registerBeanDefinition(beanName, beanDefinition);
        // 获取有参构造函数实例化对象
        User user = (User) factory.getBean(beanName, "jie");

        assertThat(user).isNotNull();
        assertThat(user.getUserInfo()).isEqualTo("jie");
    }

    @Test
    public void testBeanFactory_succeed_noParamStructure() {
        DefaultListableBeaFactory factory = new DefaultListableBeaFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        String beanName = "helloService";
        // 获取无参构造函数实例化对象
        factory.registerBeanDefinition(beanName, beanDefinition);
        HelloService helloService = (HelloService) factory.getBean(beanName);

        assertThat(helloService).isNotNull();
        assertThat(helloService.sayHello()).isEqualTo("hello");
    }

    @Test
    public void testConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] constructors = beanClass.getConstructors();
        // 单入参实例化
        Object single_obj = null;
        Object[] single_args = new Object[]{"helloService"};
        for (Constructor constructor : constructors) {
            if (constructor.getParameterTypes().length == single_args.length) {
                single_obj = beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(single_args);
            }
        }
        HelloService singleHelloService = (HelloService) single_obj;
        System.out.println(singleHelloService.getMsg());

        // 多入参实例化
        Object double_obj = null;
        Object[] double_args = new Object[]{"jie", 18};
        for (Constructor constructor : constructors) {
            if (constructor.getParameterTypes().length == double_args.length) {
                double_obj = beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(double_args);
            }
        }
        HelloService doubleHelloService = (HelloService) double_obj;
        System.out.println(doubleHelloService.getHelloService());
    }

    @Test
    public void testCglib() throws NoSuchMethodException {
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        Class beanClass = beanDefinition.getBeanClass();
        // 创建代理对象
        Enhancer enhancer = new Enhancer();
        // 设置代理目标
        enhancer.setSuperclass(beanClass);
        // 设置单一回调对象，在调用中拦截对目标方法的调用
        // NoOp回调把对方法调用直接委派到这个方法在父类中的实现（也可以理解成真实对象直接调用方法）
        enhancer.setCallback(NoOp.INSTANCE);
        // 设置类加载器
        enhancer.setClassLoader(beanClass.getClassLoader());

        // 创建无参构造实例对象
        HelloService helloService = (HelloService) enhancer.create();
        System.out.println(helloService.sayHello());

        // 创建单个入参构造实例对象
        Object[] single_obj = {"jie"};
        Constructor singleConstructor = beanClass.getDeclaredConstructor(String.class);
        HelloService singleHelloService = (HelloService) enhancer.create(singleConstructor.getParameterTypes(), single_obj);
        System.out.println(singleHelloService.getMsg());

        // 创建多个入参构造示例对象
        Object[] double_obj = {"jie", 18};
        Constructor doubleConstructor = beanClass.getDeclaredConstructor(String.class, Integer.class);
        HelloService doubleHelloService = (HelloService) enhancer.create(doubleConstructor.getParameterTypes(), double_obj);
        System.out.println(doubleHelloService.getHelloService());

    }
}
