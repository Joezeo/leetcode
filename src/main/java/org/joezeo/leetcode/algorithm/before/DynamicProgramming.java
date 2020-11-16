package org.joezeo.leetcode.algorithm.before;

/**
 * 动态规划学习
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/8/19 16:27
 */
public class DynamicProgramming {

    public static void main(String[] args) {
        fibonacci();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////                                              Fibonacci                                                    ////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void fibonacci() {
        long startTime, endTime, fibo;
        int times = 40;
        // 递归非常花费时间
        startTime = System.currentTimeMillis();
        fibo = traditionalFibonacci(times);
        endTime = System.currentTimeMillis();
        System.out.println("递归计算 -> fibo=" + fibo + " -> 花费时间=" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        fibo = fibonacciSequence(times);
        endTime = System.currentTimeMillis();
        System.out.println("动态规划 -> fibo=" + fibo + " -> 花费时间=" + (endTime - startTime));
    }

    /*
        fn = fn-1 + fn-2
        ...
        f3 = f2 + f1

        使用传统的递归计算斐波那契数列，需要计算很多重复的值，效率很低，时间复杂度为O(2^n)
     */

    // 传统的，使用递归的斐波那契数列
    public static long traditionalFibonacci(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }

        return traditionalFibonacci(n - 1) + traditionalFibonacci(n - 2);
    }

    // 使用动态规划计算斐波那契数列
    public static long fibonacciSequence(int n) {
        // 构造一个数组存储每一个斐波那契值
        long[] fiboValue = new long[n];
        fiboValue[0] = 0;
        fiboValue[1] = 1;
        for (int i = 2; i < n; i++) {
            fiboValue[i] = fiboValue[i - 1] + fiboValue[i - 2];
        }
        return fiboValue[n - 1];
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////



}
