package mi.feng.point.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: MiFeng
 * @Date: 2018/8/3 15:54
 * @Description: 示例来自 https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html
 */
public class Delegator implements InvocationHandler {

    private static Method hashCodeMethod;
    private static Method equalsMethod;
    private static Method toStringMethod;

    private Class[] interfaces;
    private Object[] delegates;

    static {
        try {
            hashCodeMethod = Object.class.getMethod("hashCode", null);
            equalsMethod = Object.class.getMethod("equals", new Class[]{ Object.class });
            toStringMethod = Object.class.getMethod("toString", null);

        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    public Delegator(Class[] interfaces, Object[] delegates){
        this.interfaces = interfaces.clone();
        this.delegates = delegates.clone();
    }

    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Class declaringClass = m.getDeclaringClass();

        if (declaringClass == Object.class){
            if (m.equals(hashCodeMethod)){
                return proxyHashCode(proxy);
            } else if (m.equals(equalsMethod)){
                return proxyEquals(proxy, args[0]);
            } else if (m.equals(toStringMethod)){
                return proxyToString(proxy);
            } else {
                throw new InternalError("unexpected Object method dispatched: " + m);
            }
        }else {
            for (int i = 0; i < interfaces.length; i++) {
                if (declaringClass.isAssignableFrom(interfaces[i])) {
                    try {
                        return m.invoke(delegates[i], args);
                    } catch (InvocationTargetException e) {
                        throw e.getTargetException();
                    }
                }
            }
        }

        return invokeNotDelegated(proxy, m, args);
    }

    protected Object invokeNotDelegated(Object proxy, Method m, Object[] args){
        throw new InternalError("unexpected method dispatched: " + m);
    }

    protected Integer proxyHashCode(Object proxy){
        System.out.println("------------------proxyHashCode------------------");
        return new Integer(System.identityHashCode(proxy));
    }

    protected Boolean proxyEquals(Object proxy, Object other){
        System.out.println("------------------proxyEquals------------------");
        return proxy == other ? Boolean.TRUE : Boolean.FALSE;
    }

    protected String proxyToString(Object proxy){
        System.out.println("------------------proxyToString------------------");
        return proxy.getClass().getName() + '@' + Integer.toHexString(proxy.hashCode());
    }
}
