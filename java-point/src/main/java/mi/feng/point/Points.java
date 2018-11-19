package mi.feng.point;

/**
 * @Auther: MiFeng
 * @Date: 2018/9/29 16:22
 * @Description:
 */
public class Points {

    public static void main(String[] args) {
        double l;
        long then = System.currentTimeMillis();

//        for (int i=0; i<10; i++){
            l = fibImpl(50);
//        }

        long now = System.currentTimeMillis();
        System.out.println("Elapsed time:" + (now - then));
    }

    private static double fibImpl(int n){
        if (n < 0) throw new IllegalArgumentException("Must be > 0");
        if(n ==0) return 0d;
        if (n ==1) return 1d;

        double d = fibImpl(n-2) + fibImpl(n-1);
        if (Double.isInfinite(d)) throw new ArithmeticException("Overflow");
        return d;
    }
}
