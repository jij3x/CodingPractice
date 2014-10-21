import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 0;
        while ((n = in.nextInt()) != -1) {
            int traveled = 0;
            for (int i = 0, prev = 0; i < n; i++) {
                int speed = in.nextInt();
                int hours = in.nextInt();
                traveled += (hours - prev) * speed;
                prev = hours;
            }
            System.out.format("%d miles\n", traveled);
        }
        in.close();
    }
}