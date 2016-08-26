import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;

/**
 * Created by ovikdevil on 25.08.16.
 */
public class Test {
    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

        ToDoubleFunction<String> fitness =
                (x) -> {
                    double i = 0;
                    for(char c : x.toCharArray()) {
                        if (c == '0')
                            i++;
                    }
                    return 1/(i+1);
        };

        System.out.println(geneticAlgorithm.run(fitness, 7, 0.6, 0.002, 10000));
    }
}
