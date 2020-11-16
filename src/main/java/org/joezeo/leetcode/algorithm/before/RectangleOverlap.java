package org.joezeo.leetcode.algorithm.before;

import java.util.Arrays;

/**
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 *
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 *
 * 给出两个矩形，判断它们是否重叠并返回结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rectangle-overlap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RectangleOverlap {
    /**
     *  二维投影法
     */
    public static  boolean isRectangleOverlap_1(int[] rec1, int[] rec2) {
        // 如果两个矩形有交集，那么它们在x、y轴上的投影直线都会存在交集
        // 两个矩形在x轴投影直线为：(rec1[0],rec1[2]) (rec2[0],rec2[2])
        // 两个矩形在y轴投影直线为：(rec1[1],rec1[3]) (rec2[1],rec2[3])
        // 两条直线的相交条件为,以x轴投影举例：min(rec1[2], rec2[2]) > max(rec1[0], rec1[0])
        return Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) &&
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]);
    }

    /**
     * 反证法
     */
    public static  boolean isRectangleOverlap_2(int[] rec1, int[] rec2) {
        // 我们来探究什么时候两个矩形 不相交
        // 当一个矩形rec2在rec1的上下左右时两个矩形不相交
        // rec2在rec1上方：rec2[1] >= rec1[3]
        // rec2在rec1下方：rec2[3] <= rec1[1]
        // rec2在rec1左方：rec2[2] <= rec1[0]
        // rec2在rec1右方：rec2[0] >= rec1[2]
        return !(rec2[1] >= rec1[3] || rec2[3] <= rec1[1] || rec2[2] <= rec1[0] || rec2[0] >= rec1[2]);
    }

}
