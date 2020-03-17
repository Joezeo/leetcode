package com.joezeo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpiltIntoFibonacci {
    public static void main(String[] args) {
        String str = "123456579";
        System.out.println(splitIntoFibonacci(str));
    }

    public static List<Integer> splitIntoFibonacci(String S) {
        if(S.length() < 3){
            return new ArrayList<Integer>();
        }
        int maxLength = Integer.valueOf(1<<31 - 1).toString().length();

        for(int i=1; i<S.length()-1 && i<maxLength; i++){ // i 为第一个数字的位数，j 为第二个数字的位数
            for(int j=1; j<S.length()-i && j<maxLength; j++){
                List<Integer> result = Fibonacci(S, i,j);
                if(result != null){
                    return result;
                }
            }
        }
        return new ArrayList<Integer>(); // 没有结果返回空集合
    }

    public static List<Integer> Fibonacci(String s,Integer i, Integer j){
        List<Integer> result = new ArrayList();
        int maxNum = 1<<31-1;
        int num1 = Integer.parseInt(s.substring(0, 0+i));
        int num2 = Integer.parseInt(s.substring(0+i, 0+i+j));
        if(num1>maxNum || num2>maxNum){
            return null;
        }
        // 有了第一，第二个数字就可以判断出出整个斐波那契数列，只需判断当前字符串是否符合即可
        String fibo = Fibonacci(num1, num2, s.length(),i+j);
        String tmp = fibo.replaceAll(",", "");
        if(s.equals(tmp)){
            String[] nums = fibo.split(",");
            return Arrays.stream(nums).map(num -> Integer.parseInt(num)).collect(Collectors.toList());
        } else {
            return null;
        }

    }

    public static String Fibonacci(int num1, int num2, int maxLength, int curLength){
        StringBuilder fibo = new StringBuilder();
        fibo.append(num1).append(",").append(num2);
        maxLength+=1;
        for(; curLength<maxLength; curLength=fibo.length(),maxLength+=1){ // maxLength+=1 是加上逗号的占位
            int num3 = num1+num2;
            fibo.append("," + num3);
            num1 = num2;
            num2 = num3;
        }
        return fibo.toString();
    }
}
