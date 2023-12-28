package com.jie.spring.aop.aspectj;

import com.jie.spring.aop.Pointcut;
import com.jie.spring.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * 将切面 Pointcut、拦截方法 Advice 和具体的拦截表达式包装在一起
 * 这样就可以在 XML 配置中定义一个 PointcutAdvisor 切面拦截器了。
 *
 * @author jie
 * @date 2023/12/27 23:00
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    /**
     * 切面
     */
    private AspectJExpressionPointcut pointcut;
    /**
     * 具体的拦截方法
     */
    private Advice advice;
    /**
     * 表达式
     */
    private String expression;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
