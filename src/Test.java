import java.util.Random;

/**
 * Created by ovikdevil on 25.08.16.
 */
public class Test {
    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        Random random = new Random();

        for (int i = 0; i < 100000; i++) {
            if (!geneticAlgorithm.generate(10).contains("0")) {
                System.out.println("ALL ONES");
            }
            if (!geneticAlgorithm.generate(10).contains("1")) {
                System.out.println("ALL ZEROES");
            }
        }
    }
}
