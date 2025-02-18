from collections import deque

N = int(input())
M = int(input())
board = [[0] * (N + 1) for _ in range(N + 1)]
for _ in range(M):
    # a > b
    a, b = map(int, input().split())
    board[a][b] = 1  # a가 b보다 크다
    board[b][a] = -1  # b가 a보다 작다
for i in range(1, N + 1):
    visited = [0] * (N + 1)
    visited[i] = 1
    smalls = set()
    bigs = set()
    for j in range(1, N + 1):
        if not visited[j]:
            if board[i][j] == 1:
                smalls.add(j)
                visited[j] = 1
            elif board[i][j] == -1:
                bigs.add(j)
                visited[j] = 1
    # i보다 작은 경우 탐색
    dq = deque(smalls)
    while dq:
        idx = dq.popleft()
        for j in range(1, N + 1):
            if not visited[j] and board[idx][j] == 1:
                smalls.add(j)
                dq.append(j)
                visited[j] = 1
    # i보다 큰 경우 탐색
    dq = deque(bigs)
    while dq:
        idx = dq.popleft()
        for j in range(1, N + 1):
            if not visited[j] and board[idx][j] == -1:
                bigs.add(j)
                dq.append(j)
                visited[j] = 1
    print(N - len(smalls) - len(bigs) - 1)
