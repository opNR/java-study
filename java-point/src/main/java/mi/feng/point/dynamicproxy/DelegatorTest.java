package mi.feng.point.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * @Auther: MiFeng
 * @Date: 2018/8/3 16:19
 * @Description:
 */
public class DelegatorTest {

    public static void main(String[] args) {
        Class[] proxyInterface = new Class[]{ Foo.class };

        Foo foo = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
                                proxyInterface,
                                new Delegator(proxyInterface, new Object[]{new FooImpl()} ));

        System.out.println(foo.bar(null));
        System.out.println(foo.toString());
    }
}
