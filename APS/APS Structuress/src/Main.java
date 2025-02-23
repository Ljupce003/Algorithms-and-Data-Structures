import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Влез
        int n = scanner.nextInt();
        int[] pesticides = new int[n];

        for (int i = 0; i < n; i++) {
            pesticides[i] = scanner.nextInt();
        }

        // Излез
        int days = daysUntilNoPlantsDie(n, pesticides);
        System.out.println(days);
    }

    public static int daysUntilNoPlantsDie(int n, int[] pesticides) {
        int days = 0;
        Stack<Integer> stack = new Stack<>();

        while (true) {
            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && pesticides[i] > pesticides[stack.peek()]) {
                    int index = stack.pop();
                    pesticides[index]--;
                }

                stack.push(i);
            }

            if (stack.size() == n) {
                break;
            }

            days++;
            stack.clear();
        }

        return days;
    }
}
