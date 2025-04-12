from collections import deque

def isValid(r, c, y, x):
    if 0 <= y < r and 0 <= x < c:
        return True
    return False


def solution(board):
    answer = float('inf')
    n = len(board)
    m = len(board[0])
    start = (-1, -1)
    end = (-1, -1)
    for i in range(n):
        for j in range(m):
            if board[i][j] == 'R':
                start = (i, j)
            elif board[i][j] == 'G':
                end = (i, j)
    visited = [[-1] * m for _ in range(n)]
    visited[start[0]][start[1]] = 0
    dirs = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    dq = deque()
    dq.append([start, 0])
    while dq:
        pos, count = dq.pop()
        y, x = pos
        if y == end[0] and x == end[1]:
            answer = min(answer, count)
        for dy, dx in dirs:
            ny = y + dy
            nx = x + dx
            if isValid(n, m, ny, nx):
                if board[ny][nx] != 'D':
                    while isValid(n, m, ny, nx):
                        if board[ny][nx] == 'D':
                            ny -= dy
                            nx -= dx
                            break
                        ny += dy
                        nx += dx
                    # 게임판의 끝에서 멈춘 경우
                    if not isValid(n, m, ny, nx):
                        ny -= dy
                        nx -= dx
                    if visited[ny][nx] == -1 or visited[ny][nx] > count + 1:
                        visited[ny][nx] = count + 1
                        dq.append([(ny, nx), count + 1])
    return answer if answer != float('inf') else -1