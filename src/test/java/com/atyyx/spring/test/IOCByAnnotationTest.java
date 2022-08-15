package com.atyyx.spring.test;

import com.atyyx.spring.Controller.UserController;
import com.atyyx.spring.Dao.UserDao;
import com.atyyx.spring.Service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class IOCByAnnotationTest {

    /**
     * @Componet:将类表示为普通组件
     * @Contrlloer
     * @Service
     * @Repository：将类标识成持久层组件
     * 加上注解以后就相当于一个bean，肯定是不能够加在一个接口上的
     *
     * 通过注解+扫描所配置的bean的id，默认值为类的小驼峰，即类名的首字母小写的结果
     * 可以通过标识组件的注解value属性值设置bean的自定义id
     */
    @Test
    @DisplayName("用注解来注册bean")
    public void test()
    {
        // 所谓的用注解标识成一个组件，其实也就是在ioc容器中有这个类对应的bean
        ApplicationContext ioc=new ClassPathXmlApplicationContext("spring-ioc-annotation.xml");
        UserController userController = ioc.getBean("userController",UserController.class);
        System.out.println(userController);
        UserService userService = ioc.getBean("userServiceImpl",UserService.class);
        System.out.println(userService);
        UserDao userDao = ioc.getBean("userDaoImpl",UserDao.class);
        System.out.println(userDao);
    }

    /**
     * @Autowired: 实现自动装配的注解
     * 1.@Autowired注解能够表示的位置：
     * a>能够表示在成员变量上，此时成员变量不需要设置set方法
     * b>标识在set方法上
     * c>表示在带成员变量的构造方法上
     * 2.@Autowired注解原理
     * a>默认通过byType的方式，在ioc容器中匹配某个bean为属性赋值
     * b>如果有多个匹配上的bean，那么就会自动通过byName的方式实现自动装配的小郭
     * 即将药赋值的属性的属性名作为bean的id来匹配某个bean的属性，为某个属性进行赋值
     * c>若byType和byName的方式都无法实现自动装配，即ioc容器有多个类型匹配的bean，且这些bean的id和要赋值的属性的属性名都不一样
     * 会抛出异常NoUniqueBeanDefinitionException
     * d>此时可以在要赋值的属性上，添加一个注解@Qualifier("userServiceImpl")，指定某个bean的id，将要用这个bean为属性进行赋值
     *
     * 注意：若ioc容器中没有任何一个匹配的bean，则抛出异常  NoSuchBeanDefinitionException
     * @Autowried注解中有一个属性required，默认值为true，要求必须完成自动装配
     * 可以将required的值设置成false，此时能装配就装配；无法装配就是用属性和默认值
     *
     */
    @Test
    public void test2()
    {
        ApplicationContext ioc=new ClassPathXmlApplicationContext("spring-ioc-annotation.xml");
        UserController userController = ioc.getBean("userController",UserController.class);
        userController.saveUser();
    }
}
