package org.joezeo.leetcode.algorithm.before;

/**
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 * <p>
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 * <p>
 * 示例：
 * LFUCache cache = new LFUCache( 2  capacity (缓存容量)  );
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回 1
 * cache.put(3,3);    // 去除 key 2
 * cache.get(2);       // 返回 -1 (未找到key 2)
 * cache.get(3);       // 返回 3
 * cache.put(4,4);    // 去除 key 1
 * cache.get(1);       // 返回 -1 (未找到 key 1)
 * cache.get(3);       // 返回 3
 * cache.get(4);       // 返回 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lfu-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Joezeo
 * @date 2020/4/5 12:49
 */
public class LFUCache {
    public static void main(String[] args) {
        LFUCache_1 cache = new LFUCache_1(2);
    }

}

/**
 * 时间复杂度均为O(n)，没有达到进阶O(1)的目标
 * leetcode执行时间为 190mm 击败10.39%，内存消耗47.9mb击败100%
 */
class LFUCache_1 {
    private class Node {
        int key;
        int value;
        int idx; // 在数组bucket中的下标
        int useCount; // 使用次数
        long lastUseTime; // 最近使用时间
    }

    private class Pair {
        int key;
        Node value;

        public Pair(int key, Node value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] bucket;
    private int capacity; // 最大容量
    private int size; // 当前容量

    public LFUCache_1(int capacity) {
        this.capacity = capacity;
        this.bucket = new Node[capacity];
        this.size = 0;
    }

    public int get(int key) {
        if (size == 0) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            Node node = bucket[i];
            if (node.key == key) {
                node.useCount++;
                node.lastUseTime = System.nanoTime();
                return node.value;
            }
        }
        return -1;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node flg = null;
        Pair lastUse = null; // key:useCount value:node
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                Node node = bucket[i];
                if (node.key == key) {
                    flg = node;
                }
                if (lastUse == null) {
                    lastUse = new Pair(node.useCount, node);
                    continue;
                }
                if (node.useCount < lastUse.key) {
                    lastUse.key = node.useCount;
                    lastUse.value = node;
                    continue;
                }
                if (node.useCount == lastUse.key && node.lastUseTime < lastUse.value.lastUseTime) {
                    lastUse.value = node;
                    continue;
                }
            }
        }

        if (flg != null) {
            flg.value = value;
            flg.useCount++;
            flg.lastUseTime = System.nanoTime();
            return;
        }
        Node node = new Node();
        node.key = key;
        node.value = value;
        node.useCount = 0;
        node.lastUseTime = System.nanoTime();
        if (size == capacity) {
            node.idx = lastUse.value.idx;
            bucket[node.idx] = node;
        } else {
            node.idx = size;
            bucket[size++] = node;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
