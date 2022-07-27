package com.toocol.lc.java;

public class Numeric {

    public static void main(String[] args) {
        System.out.println(gcd(15, -12));
    }

    /**
     * 最大公约数
     */
    public static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    public static int mul(int x, int y) {
        for (int i = Math.max(x, y); i <= x * y; i++) {
            if (i % x == 0 && i % y == 0) {
                return i;
            }
        }
        return x * y;
    }
}
