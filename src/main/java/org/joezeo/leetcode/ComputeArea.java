package org.joezeo.leetcode;

/**
 * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
 *
 * 每个矩形由其左下顶点和右上顶点坐标表示，
 */
public class ComputeArea {
    public static void main(String[] args) {
        int area = computeArea(-1500000001, 0, -1500000000, 1,1500000000, 0, 1500000001, 1);
    }

    /**
     * 使用投影法
     * 两个矩形相交，那么他们在x、y轴上的投影必定都相交
     * x轴投影线相交条件：Math.min(C, G) > Math.max(A, E)
     * y轴投影线相交条件：Math.min(D, H) > Math.max(B, F)
     */
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // 总面积
        int total = (C - A) * (D - B) + (G - E) * (H - F);

        // 判断是否相交
        if (Math.min(C, G) > Math.max(A, E) &&
                Math.min(D, H) > Math.max(B, F)) { // 相交
            return total - (Math.min(C, G) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F));
        } else { // 不相交
            return total;
        }
    }
}
