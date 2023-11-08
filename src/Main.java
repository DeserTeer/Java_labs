import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Food[] breakfast = new Food[20];
        int itemsSoFar = 0;
        boolean calories = false,
                sort = false;
        for (String arg : args) {
            if (arg.equals("-calories")) {
                calories = true;
                continue;
            } else if (arg.equals("-sort")) {
                sort = true;
                continue;
            } else {
                String[] parts = arg.split("/");
                if (parts[0].equals("Cheese")) {
                    breakfast[itemsSoFar] = new Cheese();
                } else if (parts[0].equals("Apple")) {
                    breakfast[itemsSoFar] = new Apple(parts[1]);
                } else if (parts[0].equals("Coffee")) {
                    breakfast[itemsSoFar] = new Coffee(parts[1]);
                }
                itemsSoFar++;

            }
        }
        for (Food item : breakfast)
            if (item != null)
                item.consume();
            else
                break;

        { // подсчёт продуктов
            System.out.println("\ndisplay the number of products? y/n");
            Scanner in = new Scanner(System.in);
            String ans = in.next();
            if (ans.equals("y")) {
                ArrayList<Pair<String, Integer>> counter = new ArrayList<>(0);
                for (Food item : breakfast) {
                    if (item == null) break;
                    if (counter.size() == 0) {
                        counter.add(new Pair(item.name, 1));
                        continue;
                    }
                    int i = 0;
                    for (Pair count : counter) {
                        i++;
                        if (item.name.equals(count.key)) {
                            counter.get(i - 1).value++;
                            break;
                        }
                        if (i == counter.size()) {
                            counter.add(new Pair(item.name, 1));
                            break;
                        }
                    }
                }
                for (Pair count : counter) {
                    System.out.println(count.key + " in the amount of " + count.value + "\n");
                }
            }
        }

        if (calories) { // подсчёт калорийности
            int summ = 0;
            for (Food item : breakfast)
                if (item != null)
                    summ += item.calculateCalories();
                else {
                    System.out.println("Сaloric value of breakfast is " + summ + " calories");
                    break;
                }

        }

        if (sort) { // сортировка по калорийности
            Arrays.sort(breakfast, new Comparator<Food>() {
                @Override
                public int compare(Food o1, Food o2) {
                    if (o1 == null) return 1;
                    if (o2 == null) return -1;
                    return ((Food) o1).calculateCalories() - ((Food) o2).calculateCalories();
                }
            });
            System.out.println("\nList in ascending calorie content:");
            for (Food item : breakfast) {
                if(item == null) break;
                System.out.println(item);
            }
        }

        System.out.println("\nВсего хорошего!");
    }
}
