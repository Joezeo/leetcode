package com.joezeo.algorithm.leetcode;

/**
 * 给定一组字符，使用原地算法将其压缩。
 *
 * 压缩后的长度必须始终小于或等于原数组长度。
 *
 * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
 *
 * 在完成原地修改输入数组后，返回数组的新长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-compression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CompressString {
    public static void main(String[] args) {

    }

    /**
     *  设置三个指针
     *  一个start指向一段相同字符序列的开始位置
     *  一个read指向正在读的位置
     *  一个write指向正在写的位置
     */
    public int compress(char[] chars) {
        int write=0,start=0;
        for(int read = 0; read < chars.length; read++){
            if(read + 1 >= chars.length || chars[read+1] != chars[start]){
                chars[write++] = chars[read];
                if(read > start){
                    for(char ch : ("" + (read-start+1)).toCharArray()){
                        chars[write++] = ch;
                    }
                }
                start=read+1;
            }
        }

        return write;
    }
}
