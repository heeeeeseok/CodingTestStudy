from collections import defaultdict
import heapq

def dijikstra(start):
    heap = []
    heapq.heappush(heap, [0, start])
    INF = float('inf')
    weights = [INF] * (N + 1)
    weights[start] = 0

    while heap:
        weight, node = heapq.heappop(heap)
        if weight > weights[node]:
            continue
        for n in graph[node].keys():
            W = weight + graph[node][n]
            if weights[n] > W:
                weights[n] = W
                heapq.heappush(heap, (W, n))
    return weights


N, M = map(int, input().split())

graph = [defaultdict(lambda: 1001) for _ in range(N + 1)]
visited = [0] * (N + 1)
for i in range(M):
    a, b, c = map(int, input().split())
    if (graph[a][b] > c):
        graph[a][b] = c
    if (graph[b][a] > c):
        graph[b][a] = c
result = dijikstra(1)
print(result[N])
