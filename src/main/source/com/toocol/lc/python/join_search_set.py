# -*- encoding: utf-8 -*-
# @date    : 2022/07/17 12:47:08
# @author  : Joe Zane
# @email   : joezane.cn@gmail.com
# @version : v1.0


class JoinSearchSet:
    def __init__(self):
        self.parent, self.rank = [], []
        self.maxSize = 0

    def initialise(self, capacity: int):
        self.parent = [-1] * capacity
        self.rank = [1] * capacity

    def findRoot(self, x: int) -> int:
        while self.parent[x] != -1:
            x = self.parent[x]
        return x

    def joint(self, x: int, y: int):
        rx, ry = self.findRoot(x), self.findRoot(y)
        if rx == ry:
            return
        if self.rank[rx] > self.rank[ry]:
            self.parent[ry] = rx
            self.rank[rx] += self.rank[ry]
            self.maxSize = max(self.maxSize, self.rank[rx])
        else:
            self.parent[rx] = ry
            self.rank[ry] += self.rank[rx]
            self.maxSize = max(self.maxSize, self.rank[ry])
