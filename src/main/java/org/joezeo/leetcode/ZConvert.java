package com.joezeo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ZConvert {
    public static void main(String[] args) {

    }


    /**
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 这里最重要的思路是不要被每一行的空格所迷惑
     * 上面的输出结果等同于：
     * LCIR
     * ETOESIIG
     * EDHN
     *
     * 其实就是从上到下再从下到上遍历字符串
     */
    public String convert(String s, int numRows) {

        if(numRows <= 1 || s.length()<=numRows){
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for(int i=0;i<Math.min(numRows, s.length());i++){
            rows.add(new StringBuilder());
        }

        int curRow = 0; // 当前行数
        boolean goingDown = false; // 前进方向，上或者下

        for(char ch : s.toCharArray()){
            rows.get(curRow).append(ch);
            if(curRow == 0 || curRow==numRows-1){
                goingDown = !goingDown;
            }
            curRow+=goingDown?1:-1;
        }
        StringBuilder sb = new StringBuilder();
        for(StringBuilder row : rows){
            sb.append(row);
        }
        return sb.toString();
    }
}
