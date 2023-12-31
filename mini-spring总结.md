## Day02 实现 Bean 的定义、注册、获取

当一个 Bean 对象被定义存放以后，再由 Spring 统一进行装配，这个过程包括 Bean 的初始化、属性填充等，最终我们就可以完整的使用一个
Bean 实例化后的对象了。

Bean 的定义、注册、获取三个基本步骤

我们在把系统设计的视角聚焦到具体代码实现上，你会有什么手段来实现你想要的设计模式呢？其实编码方式主要依托于：接口定义、类实现接口、抽象类实现接口、继承类、继承抽象类，而这些操作方式可以很好的隔离开每个类的基础功能、通用功能和业务功能，当类的职责清晰后，你的整个设计也会变得容易扩展和迭代。

非常重要的一点是在 Bean 注册的时候只注册一个类信息，而不会直接把实例化信息注册到 Spring 容器中。
在获取 Bean 对象时需要处理 Bean 对象的实例化操作以及判断当前单例对象在容器中是否已经缓存起来了。

## Day06 实现应用上下文，自动识别、资源加载、扩展机制

现在需要提供出一个可以在 Bean 初始化过程中，完成对 Bean 对象的扩展时，就很难做到自动化处理。所以我们要把 Bean 对象扩展机制功能和对
Spring 框架上下文的包装融合起来，对外提供完整的服务。

为了能满足于在 Bean 对象从注册到实例化的过程中执行用户的自定义操作，就需要在 Bean
的定义和初始化过程中插入接口类，这个接口再有外部去实现自己需要的服务。那么在结合对 Spring
框架上下文的处理能力，就可以满足我们的目标需求了。整体设计结构如下图：

![img](mini-spring总结.assets/spring-7-02.png)

- 满足于对 Bean 对象扩展的两个接口，其实也是 Spring 框架中非常具有重量级的两个接口：`BeanFactoryPostProcessor`
  和 `BeanPostProcessor`，也几乎是大家在使用 Spring 框架额外新增开发自己组建需求的两个必备接口。
- BeanFactoryPostProcessor，是由 Spring 框架组建提供的容器扩展机制，允许在 Bean 对象注册后但未实例化之前，对 Bean
  的定义信息 `BeanDefinition` 执行修改操作。
- BeanPostProcessor，也是 Spring 提供的扩展机制，不过 BeanPostProcessor 是在 Bean 对象实例化之后修改 Bean 对象，也可以替换
  Bean 对象。这部分与后面要实现的 AOP 有着密切的关系。
- 同时如果只是添加这两个接口，不做任何包装，那么对于使用者来说还是非常麻烦的。我们希望于开发 Spring 的上下文操作类，把相应的
  XML 加载 、注册、实例化以及新增的修改和扩展都融合进去，让 Spring 可以自动扫描到我们的新增服务，便于用户使用。

![图 7-3](mini-spring总结.assets/spring-7-03.png)

## Day07 向虚拟机注册钩子，实现Bean对象的初始化和销毁方法

可能面对像 Spring 这样庞大的框架，对外暴露的接口定义使用或者xml配置，完成的一系列扩展性操作，都让 Spring 框架看上去很神秘。其实对于这样在
Bean 容器初始化过程中额外添加的处理操作，无非就是预先执行了一个定义好的接口方法或者是反射调用类中xml中配置的方法，最终你只要按照接口定义实现，就会有
Spring 容器在处理的过程中进行调用而已。

![img](mini-spring总结.assets/spring-8-03.png)

在学习和动手实践 Spring
框架学习的过程中，特别要注意的是它对接口和抽象类的把握和使用，尤其遇到类似，A继承B实现C时，C的接口方法由A继承的父类B实现，这样的操作都蛮有意思的。也是可以复用到通常的业务系统开发中进行处理一些复杂逻辑的功能分层，做到程序的可扩展、易维护等特性。

## Day08 定义标记类型Aware接口，实现感知容器对象

一个类实现了多个接口、继承的类又继承了其他类、接口还可以和接口继承、实现接口的抽象类再由类实现抽象类方法、类A继承的类B实现了类A实现的接口C，等等。

![img](mini-spring总结.assets/spring-9-01.png)

如果说我希望拿到 Spring 框架中一些提供的资源，那么首先需要考虑以一个什么方式去获取，之后你定义出来的获取方式，在 Spring
框架中该怎么去承接，实现了这两项内容，就可以扩展出你需要的一些属于 Spring 框架本身的能力了。

在关于 Bean 对象实例化阶段我们操作过一些额外定义、属性、初始化和销毁的操作，其实我们如果像获取 Spring 一些如
BeanFactory、ApplicationContext
时，也可以通过此类方式进行实现。那么我们需要定义一个标记性的接口，这个接口不需要有方法，它只起到标记作用就可以，而具体的功能由继承此接口的其他功能性接口定义具体方法，最终这个接口就可以通过 `instanceof`
进行判断和调用了。

![图 9-3](mini-spring总结.assets/spring-9-03.png)

## Day09 Bean对象作用域以及FactoryBean的实现和使用

在集合 Spring 框架下，我们使用的 MyBatis 框架中，它的核心作用是可以满足用户不需要实现 Dao 接口类，就可以通过 xml
或者注解配置的方式完成对数据库执行 CRUD 操作，那么在实现这样的 ORM 框架中，是怎么把一个数据库操作的 Bean 对象交给 Spring
管理的呢。

因为我们在使用 Spring、MyBatis 框架的时候都可以知道，并没有手动的去创建任何操作数据库的 Bean
对象，有的仅仅是一个接口定义，而这个接口定义竟然可以被注入到其他需要使用 Dao
的属性中去了，那么这一过程最核心待解决的问题，就是需要完成把复杂且以代理方式动态变化的对象，注册到 Spring
容器中。而为了满足这样的一个扩展组件开发的需求，就需要我们在现有手写的 Spring 框架中，添加这一能力。

![img](mini-spring总结.assets/spring-10-01.png)

## Day10 基于观察者实现，容器事件和事件监听器

在功能实现上我们需要定义出事件类、事件监听、事件发布，而这些类的功能需要结合到 Spring 的
AbstractApplicationContext#refresh()，以便于处理事件初始化和注册事件监听器的操作。整体设计结构如下图：

![img](mini-spring总结.assets/spring-11-01.png)

- 在整个功能实现过程中，仍然需要在面向用户的应用上下文 `AbstractApplicationContext`
  中添加相关事件内容，包括：初始化事件发布者、注册事件监听器、发布容器刷新完成事件。
- 使用观察者模式定义事件类、监听类、发布类，同时还需要完成一个广播器的功能，接收到事件推送时进行分析处理符合监听事件接受者感兴趣的事件，也就是使用
  isAssignableFrom 进行判断。
- isAssignableFrom 和 instanceof 相似，不过 isAssignableFrom
  是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)
  结果是true，证明B可以转换成为A,也就是A可以由B转换而来。

## Day11 基于JDK和Cglib动态代理，实现AOP核心功能

AOP 的核心技术实现主要是动态代理的使用。

在把 AOP 整个切面设计融合到 Spring
前，我们需要解决两个问题，包括：`如何给符合规则的方法做代理`，`以及做完代理方法的案例后，把类的职责拆分出来`
。而这两个功能点的实现，都是以切面的思想进行设计和开发。

![img](mini-spring总结.assets/spring-12-01.png)

我们需要先来实现一个可以代理方法的 Proxy，其实代理方法主要是使用到方法拦截器类处理方法的调用 `MethodInterceptor#invoke`
，而不是直接使用 invoke 方法中的入参 Method method 进行 `method.invoke(targetObj, args)` 这块是整个使用时的差异。

AOP 切点表达式和使用以及基于 JDK 和 CGLIB 的动态代理类关系。

![图 12-2](mini-spring总结.assets/spring-12-02.png)

1、MethodInvocation（拦截器的调用）

代理对象创建后，最终的拦截工作都是交给了MethodInvocation。JDK交给了`ReflectiveMethodInvocation`
，而CGLIB交给`CglibMethodInvocation`。

![img](mini-spring总结.assets/1374196-20211116031250125-1444247266.webp)

```java
//此接口表示运行时的连接点（AOP术语）
public interface Joinpoint {
    //执行此拦截点，并进入下一个连接点
    Object proceed() throws Throwable;
    //保存当前连接点静态对象，这里一般指的是target
    Object getThis();
    //返回此静态连接点，一般就为当前的Method
    AccessibleObject getStaticPart();
}

public interface Invocation extends Joinpoint {
    //获取参数，例如方法的参数
    Object[] getArguments();
}

// 方法调用时，对这部分进行描述
public interface MethodInvocation extends Invocation {
    // 返回正在被调用得方法，返回的是当前Method对象。
    // 此时，效果同父类的AccessibleObject getStaticPart() 这个方法
    Method getMethod();
}
```

2、MethodInterceptor

MethodInterceptor 是提供给用户自定义方法拦截器的接口，需要用户自定义实现 invoke() 方法。然后通过反射读取。

## Day12 把AOP动态代理，融入到Bean的生命周期

## Day13 通过注解配置和包自动扫描的方式完成Bean对象的注册

为了可以简化 Bean 对象的配置，让整个 Bean
对象的注册都是自动扫描的，那么基本需要的元素包括：扫描路径入口、XML解析扫描信息、给需要扫描的Bean对象做注解标记、扫描Class对象摘取Bean注册的基本信息，组装注册信息、注册成Bean对象。那么在这些条件元素的支撑下，就可以实现出通过自定义注解和配置扫描路径的情况下，完成
Bean 对象的注册。

![img](mini-spring总结.assets/spring-14-01.png)

结合bean的生命周期，包扫描只不过是扫描特定注解的类，提取类的相关信息组装成BeanDefinition注册到容器中。

在XmlBeanDefinitionReader中解析`<context:component-scan />`
标签，扫描类组装BeanDefinition然后注册到容器中的操作在ClassPathBeanDefinitionScanner#doScan中实现。

- 自动扫描注册主要是扫描添加了自定义注解的类，在xml加载过程中提取类的信息，组装 BeanDefinition 注册到 Spring 容器中。
- 所以我们会用到 `<context:component-scan />` 配置包路径并在 XmlBeanDefinitionReader 解析并做相应的处理。
  *这里的处理会包括对类的扫描、获取注解信息等*
- 最后还包括了一部分关于 `BeanFactoryPostProcessor` 的使用，因为我们需要完成对占位符配置信息的加载，所以需要使用到
  BeanFactoryPostProcessor 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，修改 BeanDefinition 的属性信息。
  *这一部分的实现也为后续处理关于占位符配置到注解上做准备*

![图 14-2](mini-spring总结.assets/spring-14-02.png)

- 整个类的关系结构来看，其实涉及的内容并不多，主要包括的就是 xml 解析类 XmlBeanDefinitionReader 对
  ClassPathBeanDefinitionScanner#doScan 的使用。
- 在 doScan 方法中处理所有指定路径下添加了注解的类，拆解出类的信息：名称、作用范围等，进行创建 BeanDefinition 好用于 Bean
  对象的注册操作。
- PropertyPlaceholderConfigurer 目前看上去像一块单独的内容，后续会把这块的内容与自动加载 Bean
  对象进行整合，也就是可以在注解上使用占位符配置一些在配置文件里的属性信息。

## 拓展资料

### 1 BeanFactory 与 FactoryBean

#### 1.1 BeanFactory

BeanFactory 是所有 Spring Bean 的容器根接口，给 Spring 的容器定义一套规范。

可以说 **BeanFactory 就是 IOC 容器，是保存 Bean 的，其本质是 Factory**。

#### 1.2 FctoryBean

FactoryBean 是一个能生产对象的工厂 Bean，将 Bean 创建过程交给开发者。是一种 Bean 创建的方式，用于复杂 Bean 创建。

**本质是 Bean，最终还是要交给 BeanFactory 来管理**。

> FactoryBean 在 IOC 容器的基础上，给 Bean 的实现加上了一个简单工厂模式和装饰模式。
>

> FactoryBean 是一个接口，当在 IOC 容器中的 Bean 实现了 FactoryBean 后，通过 getBean() 获取的 Bean 对象并不是 FactoryBean
> 的实现类对象，而是这个实现类中的 getObject() 方法返回的对象。要想获取 FactoryBean 的实现类，就要 getBean(&beanName)，在
> BeanName 前面加上 &
>

