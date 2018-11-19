package mi.feng.point.collections;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: MiFeng
 * @Date: 2018/10/24 10:57
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
//        while (true) {
//            Counter.increment();
//            System.out.println("counter: " + Counter.value());
//            TimeUnit.SECONDS.sleep(1);
//        }
        String cert = "412725198806055714";
        System.out.println(getBirthByIdCard(cert));
    }

    static class Counter{
        private static AtomicInteger count = new AtomicInteger(0);

        public static void increment(){
            count.incrementAndGet();
        }

        public static int value(){
            return count.get();
        }
    }

    private static Date getBirthByIdCard(String idCard){
        String birthYear = "";

        if (idCard.length() == 15) {
            birthYear = "19" + idCard.substring(6,8) + "-" + idCard.substring(8,10) + "-" + idCard.substring(10,12);
        }

        if (idCard.length() == 18) {
            birthYear = idCard.substring(6,10) + "-" + idCard.substring(10,12) + "-" + idCard.substring(12,14);
        }
        try {
            System.out.println(birthYear);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            return df.parse(birthYear);
        } catch (ParseException e) {
        }
        return null;
    }
}
