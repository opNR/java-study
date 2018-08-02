package mi.feng.point.reflection;

import javax.swing.*;
import java.lang.reflect.*;

/**
 * @Auther: MiFeng
 * @Date: 2018/8/1 15:29
 * @Description: demo 源自网址: http://www.oracle.com/technetwork/articles/java/javareflection-1536171.html
 */
public class DumpMethods {

    public double val;

    public DumpMethods(){
    }

    public DumpMethods(int a, int b){
        System.out.println(" a = " + a + ", b = " + b);
    }

    private int add(int a, int b){
        return a + b;
    }

    public static void main(String[] args) {
        try {
            /**
             * 反射类（如Method）可在java.lang.reflect中找到。使用这些类必须遵循三个步骤。
             *  第一步是获取要操作的类的java.lang.Class对象
             *  第二步是调用getDeclaredMethods等方法，以获取该类声明的所有方法的列表。
             *  第三步是使用反射API来操纵信息
             */

            /*
             * A Simple Example
             */
            Class c = Class.forName("java.util.Stack");
            Method[] methods = c.getDeclaredMethods();
            for (int i=0; i<methods.length; i++){
                System.out.println(methods[i]);
            }
            System.out.println("==================================== Demo1 end ====================================");


            /*
             * Simulating the instanceof Operator
             */
            Class cls = Class.forName("mi.feng.point.reflection.A");
            boolean b1 = cls.isInstance(new Integer(37));
            System.out.println(b1);

            boolean b2 = cls.isInstance(new A());
            System.out.println(b2);
            System.out.println("---------------------- 1 ---------------------------");

            /*
             *  Finding Out About Methods of a Class
             */
            Method[] methlist = cls.getDeclaredMethods();
            for (int i=0; i<methlist.length; i++){
                Method method = methlist[i];
                System.out.println("method name = " + method.getName());
                System.out.println("decl class = " + method.getDeclaringClass());

                Class[] pvec = method.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #" + j + " " + pvec[j]);

                Class[] evec = method.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " " + evec[j]);

                System.out.println("return type = " + method.getReturnType());
            }
            System.out.println("-------------------- 2 -----------------------------");

            /*
             *  Obtaining Information About Constructors
             */
            Constructor[] ctorlist = cls.getDeclaredConstructors();
            for (int ii = 0; ii < ctorlist.length; ii++) {
                Constructor ct = ctorlist[ii];
                System.out.println("name = " + ct.getName());
                System.out.println("decl class = " + ct.getDeclaringClass());

                Class[] pvec2 = ct.getParameterTypes();
                for (int j = 0; j < pvec2.length; j++)
                    System.out.println("param #" + j + " " + pvec2[j]);

                Class[] evec2 = ct.getExceptionTypes();
                for (int j = 0; j < evec2.length; j++)
                    System.out.println("exc #" + j + " " + evec2[j]);
            }
            System.out.println("-------------------- 3 -----------------------------");

            /*
             *  Finding Out About Class Fields
             */
            Field[] fieldlist = cls.getDeclaredFields();
            for (int i = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
                System.out.println("name = " + fld.getName());
                System.out.println("decl class = " + fld.getDeclaringClass());
                System.out.println("type = " + fld.getType());

                int mod = fld.getModifiers();
                System.out.println("modifiers = " + Modifier.toString(mod));
            }
            System.out.println("==================================== Demo2 end ====================================");


            /*
             * Invoking Methods by Name
             */
            Class cls1 = Class.forName("mi.feng.point.reflection.DumpMethods");

            Class[] partypes = new Class[2];
            partypes[0] = Integer.TYPE;
            partypes[1] = Integer.TYPE;

            Method meth = cls1.getDeclaredMethod("add", partypes);

            Object[] arglist = new Object[2];
            arglist[0] = new Integer(37);
            arglist[1] = new Integer(47);
            DumpMethods methobj = new DumpMethods();

            Object retobj = meth.invoke(methobj, arglist);

            Integer retval = (Integer)retobj;
            System.out.println(retval.intValue());

            System.out.println("==================================== Demo3 end ====================================");

            /*
             * Creating New Objects
             */
            Class cls2 = Class.forName("mi.feng.point.reflection.DumpMethods");

            Class[] params = new Class[2];
            params[0] = Integer.TYPE;
            params[1] = Integer.TYPE;
            Constructor constructor = cls2.getConstructor(params);

            Object[] argArr = new Object[2];
            argArr[0] = new Integer(10);
            argArr[1] = new Integer(20);

            Object obj = constructor.newInstance(argArr);
            System.out.println(obj);

            System.out.println("==================================== Demo4 end ====================================");

            /*
             * Changing Values of Fields
             */
            Class cls3 = Class.forName("mi.feng.point.reflection.DumpMethods");
            Field field = cls3.getField("val");
            DumpMethods dumpMethods = new DumpMethods();
            field.setDouble(dumpMethods, 3.1415);
            System.out.println(" val = " + dumpMethods.val);

            System.out.println("==================================== Demo5 end ====================================");

            /*
             * Using Arrays
             */
            Class class1 = Class.forName("java.lang.String");
            Object arr = Array.newInstance(class1, 10);
            Array.set(arr, 5, "this is test");
            String str = (String) Array.get(arr, 5);
            System.out.println(str);

            System.out.println("==================================== Demo6 end ====================================");


            /*
             * A more complex manipulation of arrays
             *  Note here that a multi-dimensional array is actually an array of arrays (请注意，多维数组实际上是一个数组数组)
             */
            int dims[] = new int[]{5, 10, 15};
            Object arr1 = Array.newInstance(Integer.TYPE, dims);

            Object arrobj = Array.get(arr1, 3);
            Class cls5 = arrobj.getClass().getComponentType();
            System.out.println(cls5);
            arrobj = Array.get(arrobj, 5);
            Array.setInt(arrobj, 10, 37);

            int arrcast[][][] = (int[][][])arr;
            System.out.println(arrcast[3][5][10]);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class A {

    private double d;

    public static final int i = 37;

    String s = "testing";

    public A(){}

    protected A(int i, double d){}

    private int f1(Object p, int x) throws NullPointerException{
        if (p == null)
            throw new NullPointerException();
        return x;
    }
}
