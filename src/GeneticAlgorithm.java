import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

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

    public String mutate(String chromosome, double p) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < chromosome.length(); i++) {
            boolean b = (chromosome.charAt(i) == '1');
            if (random.nextDouble()<=p) {
                b = !b;
            }
            line.append((b?'1':'0'));
        }
        return line.toString();
    }

    public List<String> crossover(String chromosome1, String chromosome2) {
        int index = random.nextInt(chromosome1.length()-1);
        StringBuilder new_chromosome1 = new StringBuilder();
        StringBuilder new_chromosome2 = new StringBuilder();

        for (int i = 0; i < chromosome1.length(); i++) {
            if (i<=index) {
                new_chromosome1.append(chromosome1.charAt(i));
                new_chromosome2.append(chromosome2.charAt(i));
            }
            else {
                new_chromosome1.append(chromosome2.charAt(i));
                new_chromosome2.append(chromosome1.charAt(i));
            }
        }

        List<String> crossover_List = new ArrayList<>();
        crossover_List.add(new_chromosome1.toString());
        crossover_List.add(new_chromosome2.toString());
        return crossover_List;
    }

    public int rouletteSelect(List<Double> weight) {
        double weight_sum = 0;
        for(int i=0; i<weight.size(); i++) {
            weight_sum += weight.get(i);
        }
        // get a random value
        double value = random.nextDouble() * weight_sum;
        // locate the random value based on the weights
        for(int i=0; i<weight.size(); i++) {
            value -= weight.get(i);
            if(value <= 0) {
                return i;
            }
        }
        // only when rounding errors occur
        return weight.size() - 1;
    }

    public List<String> select_from_base(ToDoubleFunction<String> fitness, List<String> population) {
        List<String> selected_List = new ArrayList<>();
        List<Double> fitness_List = population
                .stream()
                .map(fitness::applyAsDouble)
                .collect(Collectors.toList());

        while (selected_List.size()<2) {
            int index = rouletteSelect(fitness_List);
            if (!selected_List.contains(population.get(index))) {
                selected_List.add(population.get(index));
            }
        }
        return selected_List;
    }

    public void selection(ToDoubleFunction<String> fitness, List<String> population, double p_c, double p_m) {
        List<String> selected_List = select_from_base(fitness, population);

        if (random.nextDouble() < p_c) { //Crossover prob
            selected_List = crossover(selected_List.get(0), selected_List.get(1));
        }

        selected_List.set(0, mutate(selected_List.get(0), p_m));
        selected_List.set(1, mutate(selected_List.get(1), p_m));

        population.addAll(selected_List);

    }



    public String run(ToDoubleFunction<String> fitness, int length, double p_c, double p_m) {
        List<String> base_population = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            base_population.add(generate(length));
        }
        List<String> new_population = select_from_base(fitness, base_population);

        while (new_population.size() < base_population.size()) {
            selection(fitness, new_population, p_c, p_m);
        }

        double max_fit = 0;
        String result_chromo = "";

        for (String chromo : new_population) {
            double fit = fitness.applyAsDouble(chromo);
            if (fit == 1) {
                return chromo;
            }
            if (fit > max_fit) {
                result_chromo = chromo;
                max_fit = fit;
            }
        }

        return result_chromo;
    }

    public String run(ToDoubleFunction<String> fitness, int length, double p_c, double p_m, int iterations) {
        List<String> base_population = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            base_population.add(generate(length));
        }
        List<String> new_population = select_from_base(fitness, base_population);

        int it = 0;

        while (it < iterations) {
            selection(fitness, new_population, p_c, p_m);
            it++;
        }

        double max_fit = 0;
        String result_chromo = "";

        for (String chromo : new_population) {
            double fit = fitness.applyAsDouble(chromo);
            if (fit == 1) {
                return chromo;
            }
            if (fit > max_fit) {
                result_chromo = chromo;
                max_fit = fit;
            }
        }

        return result_chromo;
    }

}
