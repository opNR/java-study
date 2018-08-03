package mi.feng.point.dynamicproxy;

/**
 * @Auther: MiFeng
 * @Date: 2018/8/3 15:38
 * @Description:
 */
public class DebugProxyTest {

    public static void main(String[] args) {

        Foo foo = (Foo)DebugProxy.newInstance(new FooImpl());

        System.out.println(foo.bar(null));
    }
}
