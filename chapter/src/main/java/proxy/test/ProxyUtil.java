package proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工具类
 */
public class ProxyUtil {

    /**
     * 生成代理类
     * @param bigStar
     * @return
     */
    public static Star createProxy(BigStar bigStar){
        Star star = (Star) Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),//类加载器:用于指定哪个类加载器去加载代理对象
                new Class[]{Star.class},         //代理对象需要实现的接口，可以指定多个接口
                new InvocationHandler() {        //生成代理对象的执行代码
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        /**
                         * 参数一：代理对象
                         * 参数二：要执行的方法
                         * 参数三：执行方法的参数
                         */
                        //1.获取代理对象的方法
                        String methodName = method.getName();
                        if ("sing".equals(methodName)){
                            //2.判断是否是sing方法
                            //3.增强方法
                            System.out.println("我是经纪人，我要收钱！");
                            Object result = method.invoke(bigStar, args);
                            System.out.println("我是经纪人，我要提成！");
                            return result;
                        }
                        //2.判断是否是sing方法
                        return null;
                    }
                }
        );
        return star;
    }
}
