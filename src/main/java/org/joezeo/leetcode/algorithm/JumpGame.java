package org.joezeo.leetcode.algorithm;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author Joezeo
 * @date 2020/5/4 8:01
 */
public class JumpGame {
    // 官方题解方法二
    public int jump_zhengxiang(int[] nums) {
        // 设定边界值，从左到右遍历数组，如果当前下标抵达边界值，更新边界值
        int cnt = 0;
        int end = 0;
        int maxPos = 0;
        for(int i=0; i<nums.length-1; i++){
            maxPos = Math.max(maxPos, nums[i]+i);
            if(i == end){
                end = maxPos;
                cnt++;
            }
        }
        return cnt;
    }

    // 参照官方题解的方法一，使用贪心算法，使用反向倒推，从左到右获取最近的一个抵达最后一个位置的，再依次类推
    public int jump_fanxiang(int[] nums) {
        int cnt = 0;
        int index = nums.length-1;
        while(index > 0){
            for(int i=0; i<index; i++){
                if(nums[i] + i >= index){
                    cnt++;
                    index = i;
                    break;
                }
            }
        }
        return cnt;
    }

    // 使用动态规划
    // 失败了，这种写法无法考虑到nums[i]==0的情况，因为在倒数第二个位置上的边界值的设定上没有考虑==0的情况
    public int jump_failed(int[] nums) {
        int len = nums.length;
        // 定义数组存储各种情况下抵达终点使用的次数
        int[][] ans = new int[len][];
        // 设定边界值，即数组倒数第二个位置上到最后一个位置的次数都为1
        ans[len-2] = new int[nums[len-2]];
        for(int i=0; i<nums[len-2]; i++){
            ans[len-2][i] = 1;
        }

        // 开始倒推
        for(int i=len-3; i>=0; i--){
            ans[i] = new int[nums[i]];
            for(int j=0; j<nums[i]; j++){
                int pos = i+j+1;
                if(pos >= len-1){ // 抵达终点
                    ans[i][j] = 1;
                } else {
                    int min = Integer.MAX_VALUE;
                    for(int k=0; k<ans[pos].length; k++){
                        min = Math.min(ans[pos][k], min);
                    }
                    ans[i][j] = min+1;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int k=0; k<ans[0].length; k++){
            min = Math.min(ans[0][k], min);
        }
        return min;
    }
}
