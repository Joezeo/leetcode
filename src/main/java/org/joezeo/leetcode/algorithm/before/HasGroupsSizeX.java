package org.joezeo.leetcode.algorithm.before;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一副牌，每张牌上都写着一个整数。
 * <p>
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * <p>
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HasGroupsSizeX {
    public static void main(String[] args) {

    }

    /**
     * 因为X组的每组卡牌出现次数都是X张
     * 求每个卡牌出现次数之间的最大公约数
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {
        int[] counter = new int[10000];
        for (int dk : deck) {
            counter[dk]++;
        }

        int x = 0;
        for (int cnt : counter) {
            if (cnt > 0) {
                x = gcd(x, cnt);

                if (x == 1) {
                    return false;
                }
            }
        }
        return x >= 2;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
