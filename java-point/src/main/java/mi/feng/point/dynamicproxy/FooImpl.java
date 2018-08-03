package mi.feng.point.dynamicproxy;

/**
 * @Auther: MiFeng
 * @Date: 2018/8/3 15:30
 * @Description:
 */
public class FooImpl implements Foo {

    @Override
    public Object bar(Object obj) {
        return "Foo Impl";
    }
}
