package org.joezeo.leetcode.algorithm.before;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * 示例 1：
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 *
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 *
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 *
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *  
 *
 * 提示：
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 *  
 *
 * 进阶：
 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoZhe
 * @email joezane.cn@gmail.com
 * @date 2020/10/19 15:57
 */
public class BackspaceCompare {
    /**
     * 方法一：
     * 使用栈来解决问题，暴力解决
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare_stack(String S, String T) {

        S = dealOringin(S);
        T = dealOringin(T);

        if (S.length() != T.length()) {
            return false;
        }

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != T.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public String dealOringin(String origin) {
        Stack<Character> resultStack = new Stack<>();
        for (char c : origin.toCharArray()) {
            if (c == '#') {
                if (!resultStack.empty()) {
                    resultStack.pop();
                }
            } else {
                resultStack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!resultStack.empty()) {
            sb.append(resultStack.pop());
        }
        return sb.toString();
    }

    /**
     * 方法二：双指针
     * 思路及算法
     * 一个字符是否会被删掉，只取决于该字符后面的退格符，而与该字符前面的退格符无关。因此当我们逆序地遍历字符串，就可以立即确定当前字符是否会被删掉。
     * 具体地，我们定义 skip 表示当前待删除的字符的数量。每次我们遍历到一个字符：
     * 若该字符为退格符，则我们需要多删除一个普通字符，我们让 skip 加 11；
     * 若该字符为普通字符：
     * 若 skip 为 0，则说明当前字符不需要删去；
     * 若 skip 不为 0，则说明当前字符需要删去，我们让 skip 减 1。
     */
}
