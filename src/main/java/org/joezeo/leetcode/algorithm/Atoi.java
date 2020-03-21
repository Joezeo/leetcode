package org.joezeo.leetcode.algorithm;

import java.util.*;

public class Atoi {
    public static void main(String[] args) {
        String str = "10522545459";
        System.out.println(myAtoi(str));
    }

    /**
     *  最复杂的即是判断各种边界条件
     */
    public static int myAtoi(String str) {
        int len = str.length();

        // 去除前导空格
        int index = 0;
        while (index < len) {
            if (str.charAt(index) != ' ') {
                break;
            }
            index++;
        }

        if (index == len) {
            return 0;
        }

        // 第 1 个字符如果是符号，判断合法性，并记录正负
        int sign = 1;
        char firstChar = str.charAt(index);
        if (firstChar == '+') {
            index++;
            sign = 1;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        // 不能使用 long 类型，这是题目说的
        int res = 0;
        while (index < len) {
            char currChar = str.charAt(index);
            // 判断合法性
            if (currChar > '9' || currChar < '0') {
                break;
            }

            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判断乘以 10 以后是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            // 每一步都把符号位乘进去
            res = res * 10 + sign * (currChar - '0');
            index++;
        }

        return res;
    }


    public static int myAtoi_hard(String str) {
        str = str.trim();
        char flag = '+';
        char firstNum;
        char[] nums = str.toCharArray();
        char[] legal = {'0','1','2','3','4','5','6','7','8','9'};
        Stack<Character> res = new Stack<>();
        List<Character> legalList = new ArrayList<>();
        for(char ch : legal){
            legalList.add(ch);
        }
        for(int i=0; i<nums.length; i++){
            if(nums[i]!=' '){
                if(i==0 && (nums[i]=='+' || nums[i]=='-')){
                    if(nums[i] == '-'){
                        flag = '-';
                    }
                    res.push(nums[i]);
                    continue;
                }


                if(legalList.contains(nums[i])){
                    res.push(nums[i]);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        long mul = 1;
        long result = 0;

        while(!res.isEmpty()){
            if(res.size()==1){
                char ch = res.pop();
                if(ch=='-'){
                    result = 0-result;
                } else if(ch=='+') {
                    break;
                } else {
                    long i = Long.valueOf(""+ch);
                    if(i == 0){
                        i = 1;
                    }
                    if(result+i*mul > Integer.MAX_VALUE){
                        if(flag == '-'){
                            return Integer.MIN_VALUE;
                        } else {
                            return Integer.MAX_VALUE;
                        }

                    }
                    result = result + i*mul;
                    mul*=10;
                }
            } else {
                char ch = res.pop();
                long i = Long.valueOf(""+ch);
                if(i == 0){
                    i = 1;
                }
                if(result+i*mul > Integer.MAX_VALUE){
                    if(flag == '-'){
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }

                }

                result = result + i*mul;
                mul*=10;
            }
        }

        return (int)result;
    }
}
