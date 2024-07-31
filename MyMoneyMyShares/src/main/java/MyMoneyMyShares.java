import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class MyMoneyMyShares
    {
        static class Person {
            String name;
            int amountPaid;
            List<Integer> apples;

            Person(String name, int amountPaid) {
                this.name = name;
                this.amountPaid = amountPaid;
                this.apples = new ArrayList<>();
            }

            void addApple(int weight) {
                apples.add(weight);
            }

            int totalWeight() {
                return apples.stream().mapToInt(Integer::intValue).sum();
            }

            @Override
            public String toString() {
                return name + ": " + apples.toString();
            }
        }
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<Integer> appleWeights = new ArrayList<>();

            // Input apple weights
            System.out.println("Enter apple weight in grams (-1 to stop): ");
            while (true) {
                int weight = scanner.nextInt();
                if (weight == -1) {
                    break;
                }
                appleWeights.add(weight);
            }

            // Create persons
            Person ram = new Person("Ram", 50);
            Person sham = new Person("Sham", 30);
            Person rahim = new Person("Rahim", 20);

            // Total amount paid
            int totalPaid = ram.amountPaid + sham.amountPaid + rahim.amountPaid;

            // Calculate weight allocation
            int totalWeight = appleWeights.stream().mapToInt(Integer::intValue).sum();
            int ramTarget = (totalWeight * ram.amountPaid) / totalPaid;
            int shamTarget = (totalWeight * sham.amountPaid) / totalPaid;
            int rahimTarget = (totalWeight * rahim.amountPaid) / totalPaid;

            // Distribute apples
            for (int weight : appleWeights) {
                if (ramTarget > 0 && weight <= ramTarget) {
                    ram.addApple(weight);
                    ramTarget -= weight;
                } else if (shamTarget > 0 && weight <= shamTarget) {
                    sham.addApple(weight);
                    shamTarget -= weight;
                } else if (rahimTarget > 0 && weight <= rahimTarget) {
                    rahim.addApple(weight);
                    rahimTarget -= weight;
                }
            }

            // Output distribution result
            System.out.println("Distribution Result:");
            System.out.println(ram);
            System.out.println(sham);
            System.out.println(rahim);

            scanner.close();
        }

    }
