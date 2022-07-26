package com.toocol.lc.java;

import java.util.Random;

public class Skiplist {
    private int level = 10;
    private SkiplistNode head = new SkiplistNode(-1);
    private Random random = new Random();

    public Skiplist() {
        
    }
    
    public boolean search(int target) {
        SkiplistNode[] searchLine = new SkiplistNode[level];
        find(target, searchLine);
        return searchLine[0].forward[0] != null && searchLine[0].forward[0].val == target;
    }
    
    /**
     * 插入时从下往上开始插，总共插入层数由1/2的概率来决定
     */
    public void add(int num) {
        SkiplistNode[] searchLine = new SkiplistNode[level];
        find(num, searchLine);
        SkiplistNode node = new SkiplistNode(num);
        for (int i = 0; i <= randomLevel(); i++) {
            node.forward[i] = searchLine[i].forward[i];
            searchLine[i].forward[i] = node;
        }
    }
    
    public boolean erase(int num) {
        SkiplistNode[] searchLine = new SkiplistNode[level];
        find(num, searchLine);
        SkiplistNode node = searchLine[0].forward[0];
        if (node == null || node.val != num) return false;
        for (int i = 0; i < level && searchLine[i].forward[i] == node; i++) {
            searchLine[i].forward[i] = searchLine[i].forward[i].forward[i];
        }
        return true;
    }

    /**
     * 查询节点是从上层向下层开始寻找
     */
    private void find(int target, SkiplistNode[] searchLine) {
        // searchLine 存储每一层符合条件(严格小于target)的最后一个节点
        SkiplistNode cur = head;
        for (int i = level - 1; i >= 0; i--) {
            while (cur.forward[i] != null && cur.forward[i].val < target) {
                cur = cur.forward[i];
            }
            // searchLine 存入的实际上是一个头节点，其forward[i]才是真正的跳跃表节点
            searchLine[i] = cur;
        }
    }

    private int randomLevel() {
        int lv = 1;
        for (; lv < this.level; lv++) {
            if (random.nextInt(2) == 0) break;
        }
        return lv;
    }

    class SkiplistNode {
        // 各个level层级的下一节点
        public SkiplistNode[] forward = new SkiplistNode[level];
        public int val;

        public SkiplistNode(int val) {
            this.val = val;
        }
    }
}
