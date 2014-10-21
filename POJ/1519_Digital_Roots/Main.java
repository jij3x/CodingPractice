import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String num;
        while (!(num = in.next()).equals("0")) {
            int ttl = 0;
            for (int i = 0; i < num.length(); i++)
                ttl += num.charAt(i) - '0';
            ttl %= 9;
            System.out.println(ttl == 0 ? 9 : ttl);
        }
        in.close();
    }
}