from collections import deque


M, N, K = map(int, input().split())
rectangles = []
for _ in range(K):
    rectangles.append(list(map(int, input().split())))

board = [[0] * N for _ in range(M)]
for x1, y1, x2, y2 in rectangles:
    for x in range(x1, x2):
        for y in range(y1, y2):
            board[y][x] = 1

areas = []
visited = [[False] * N for _ in range(M)]
dirs = [[0, 1], [0, -1], [1, 0], [-1, 0]]
for y in range(M):
    for x in range(N):
        if board[y][x] == 0 and not visited[y][x]:
            visited[y][x] = True
            dq = deque()
            dq.append((x, y))
            area = 1
            while dq:
                cx, cy = dq.popleft()
                for dx, dy in dirs:
                    nx = cx + dx
                    ny = cy + dy
                    if 0 <= nx < N and 0 <= ny < M and board[ny][nx] == 0 and not visited[ny][nx]:
                        visited[ny][nx] = True
                        dq.append((nx, ny))
                        area += 1
            areas.append(area)
print(len(areas))
areas.sort()
print(*areas)
