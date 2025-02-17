from collections import deque
MAX = float('inf')


N, Q = map(int, input().split())
graph = [[] for _ in range(N + 1)]
for _ in range(N - 1):
    p, q, r = map(int, input().split())
    graph[p].append((q, r))
    graph[q].append((p, r))
for _ in range(Q):
    k, v = map(int, input().split())
    answer = 0
    dq = deque()
    visited = [0] * (N + 1)
    visited[v] = 1
    for nv, nr in graph[v]:
        # next, min(r)
        dq.append((nv, nr))
    while dq:
        cv, cr = dq.popleft()
        if cr >= k:
            answer += 1
        visited[cv] = 1
        for nv, nr in graph[cv]:
            if not visited[nv]:
                dq.append((nv, min(cr, nr)))
    print(answer)

