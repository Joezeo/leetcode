from ast import List
import random


class SkiplistNode:
    def __init__(self, level: int, val: int):
        self.forward = [None] * level
        self.val = val


class Skiplist:

    def __init__(self):
        self.level = 10
        self.head = SkiplistNode(self.level, -1)

    def search(self, target: int) -> bool:
        searchs = [None] * self.level
        self.find(target, searchs)
        return searchs[0].forward[0] is not None \
            and searchs[0].forward[0].val == target

    def add(self, num: int) -> None:
        searchs = [None] * self.level
        self.find(num, searchs)
        node = SkiplistNode(self.level, num)
        for i in range(self.randomLevel()):
            node.forward[i] = searchs[i].forward[i]
            searchs[i].forward[i] = node

    def erase(self, num: int) -> bool:
        searchs = [None] * self.level
        self.find(num, searchs)
        node = searchs[0].forward[0]
        if node is None or node.val != num:
            return False
        for i in range(self.level):
            if searchs[i].forward[i] != node:
                break
            searchs[i].forward[i] = node.forward[i]
        return True

    def find(self, target: int, searchs: List[SkiplistNode]) -> None:
        cur = self.head
        for i in range(1, self.level+1):
            r = self.level - i
            while cur.forward[r] is not None and cur.forward[r].val < target:
                cur = cur.forward[r]
            searchs[r] = cur

    def randomLevel(self) -> int:
        lv = 1
        for r in range(1, self.level+1):
            if random.randint(0, 2) == 1:
                break
            lv = r
        return lv
