#include <vector>
#include <numeric>

using namespace std;

class JoinSearchSet
{
private:
    vector<int> parent;
    vector<int> rank;

public:
    int maxSize = 1;
    JoinSearchSet(int capacity)
        : parent(capacity, -1),
          rank(capacity, 1)
    {
    }

    int findRoot(int x)
    {
        while (parent[x] != -1)
            x = parent[x];
        return x;
    }

    void joinit(int x, int y)
    {
        int rx = findRoot(x), ry = findRoot(y);
        if (rx == ry)
            return;
        if (rank[rx] > rank[ry])
        {
            parent[ry] = rx;
            rank[rx] += rank[ry];
            maxSize = max(maxSize, rank[rx]);
        }
        else
        {
            parent[rx] = ry;
            rank[ry] += rank[rx];
            maxSize = max(maxSize, rank[ry]);
        }
    }
};

typedef JoinSearchSet JSS;