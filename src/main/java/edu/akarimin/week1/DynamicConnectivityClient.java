package edu.akarimin.week1;

import java.util.Scanner;

public class DynamicConnectivityClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        UF uf = new UF(n);
        while (scanner.hasNext()) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                System.out.println(p + " : " + q);
            }
        }
    }
}
