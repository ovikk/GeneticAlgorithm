import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;

/**
 * Created by ovikdevil on 25.08.16.
 */
public class GeneticAlgorithm {

    private Random random = new Random();

    public String generate(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (random.nextBoolean())
                stringBuilder.append(0);
            else
                stringBuilder.append(1);
        }
        return stringBuilder.toString();
    }

    private String[] select(List<String> population, List<Double> fitnesses) {
        // TODO: Implement the select method
    }

/*
    private String mutate(String chromosome, double p) {
        // TODO: Implement the mutate method
    }

    private String[] crossover(String chromosome1, String chromosome2) {
        // TODO: Implement the crossover method
    }

    public String run(ToDoubleFunction<String> fitness, int length, double p_c, double p_m) {
        // TODO: Implement the run method
    }

    public String run(ToDoubleFunction<String> fitness, int length, double p_c, double p_m, int iterations) {
        // TODO: Implement the run method
    }*/

}
