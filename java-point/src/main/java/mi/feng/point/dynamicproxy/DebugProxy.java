package mi.feng.point.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: MiFeng
 * @Date: 2018/8/3 15:31
 * @Description: 示例来自 https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html
 */
public class DebugProxy implements InvocationHandler {

    private Object obj;

    public static Object newInstance(Object obj){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                                    obj.getClass().getInterfaces(),
                                    new DebugProxy(obj));
    }

    private DebugProxy(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;

        try {
            System.out.println("before method " + method.getName());

            result = method.invoke(obj, args);

        } catch (InvocationTargetException e){
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        } finally {

            System.out.println("after method " + method.getName());
        }

        return result;
    }
}
