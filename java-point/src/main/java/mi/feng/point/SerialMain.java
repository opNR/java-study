package mi.feng.point;

import java.util.Date;

/**
 * @Auther: MiFeng
 * @Date: 2018/11/16 15:00
 * @Description:
 */
public class SerialMain {

    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generate(1000, 1000);
        double[][] matrix2 = MatrixGenerator.generate(1000, 1000);
        double[][] result = new double[matrix1.length][matrix2[0].length];

        Date start = new Date();

        SerialMultiplier.multiply(matrix1, matrix2, result);
//        ParallelIndividualMultiplier.multiply(matrix1, matrix2, result);

        Date end = new Date();
        System.out.printf("Serial: %d%n", end.getTime() - start.getTime());
    }
}
