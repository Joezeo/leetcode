package com.joezeo.algorithm.leetcode;

/**
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。
 * 若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/compress-string-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CompactString {
    public static void main(String[] args) {
        String source = "aaaffffffffffsssssssssssssfdfd";
        String compact = compressString(source);
        System.out.println("压缩前："+source);
        System.out.println("压缩后："+compact);
    }

    public static String compressString(String S) {
        StringBuilder compact = new StringBuilder();
        byte[] bytes = S.getBytes();
        byte tmp = 0;
        int count = 1;
        for(int i=0; i<S.length(); i++){
            if(tmp == bytes[i]){
                count ++;
            }else {
                if(i != 0){
                    compact.append((char)tmp).append(count);
                }
                tmp = bytes[i];
                count = 1;
            }

            if(i==S.length()-1){
                compact.append((char)tmp).append(count);
            }
        }
        return compact.toString().length()>=S.length()?S:compact.toString();
    }
}
