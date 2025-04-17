from collections import deque
from copy import deepcopy


N = int(input())
board = []
for i in range(N):
    board.append(list(map(int, input().split())))

visited = [[0] * N for _ in range(N)]
idx = 1
for i in range(N):
    for j in range(N):
        if board[i][j] == 1 and visited[i][j] == 0:
            dq = deque([(i, j)])
            visited[i][j] = idx
            while dq:
                y, x = dq.popleft()
                for dy, dx in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                    ny = y + dy
                    nx = x + dx
                    if 0 <= ny < N and 0 <= nx < N and board[ny][nx] == 1 and visited[ny][nx] == 0:
                        visited[ny][nx] = idx
                        dq.append((ny, nx))
            idx += 1

answer = 0
while True:
    next = deepcopy(visited)
    for i in range(N):
        for j in range(N):
            if visited[i][j] > 0:
                curIdx = visited[i][j]
                for dy, dx in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                    ny = i + dy
                    nx = j + dx
                    if 0 <= ny < N and 0 <= nx < N:
                        if next[ny][nx] > 0 and next[ny][nx] != curIdx:
                            print(answer + 1)
                            exit()
                        elif visited[ny][nx] == 0:
                            next[ny][nx] = curIdx
    
    for i in range(N):
        print(next[i])

    for i in range(N):
        for j in range(N):
            if next[i][j] > 0:
                for dy, dx in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                        ny = i + dy
                        nx = j + dx
                        if 0 <= ny < N and 0 <= nx < N and next[ny][nx] > 0 and next[ny][nx] != next[i][j]:
                            print(answer + 2)
                            exit()

    visited = next
    answer += 2
