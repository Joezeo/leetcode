package com.toocol.lc.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class CourseSchedule {

    public static void main(String[] args) {
        CourseSchedule course = new CourseSchedule();
        int[][] grah = new int[][] {
                { 1, 8 }, { 1, 3 }, { 2, 3 }, { 2, 5 }, { 8, 9 }, { 3, 4 }, { 5, 4 }, { 5, 6 }, { 9, 7 }, { 4, 7 },
                { 4, 6 }
        };
        int[] ans = course.courseSchedule(9, grah);
        for (int i : ans)
            System.out.print(i + " ");
    }

    public int[] courseSchedule(int n, int[][] grah) {
        int[][] penes = new int[n][2];
        Map<Integer, Set<Integer>> grahMap = new HashMap<>();
        for (int[] edge : grah) {
            penes[edge[1] - 1][0]++;
            penes[edge[1] - 1][1] = edge[1];
            penes[edge[0] - 1][1] = edge[0];
            Set<Integer> set = grahMap.getOrDefault(edge[0], new HashSet<>());
            set.add(edge[1]);
            grahMap.put(edge[0], set);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        for (int[] pene : penes)
            queue.offer(pene);

        int[] ans = new int[n];
        int[] tmp = new int[] { -1 };
        int idx = 0;
        while (!queue.isEmpty()) {
            int[] pe = queue.poll();
            if (pe[1] == -1) continue;
            int cur = pe[1];
            ans[idx++] = cur;
            Set<Integer> set = grahMap.get(cur);
            if (set == null)
                continue;
            for (int[] pene : queue) {
                if (set.contains(pene[1])) {
                    pene[0]--;
                }
            }
            // triger PriorityQueue to resort
            queue.add(tmp);
            queue.remove(tmp);
        }

        return ans;
    }

}
