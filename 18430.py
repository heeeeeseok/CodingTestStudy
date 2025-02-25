def is_possible(y, x, block):
    global board, visited
    for dy, dx in block:
        ny = y + dy
        nx = x + dx
        if ny < 0 or ny >= N or nx < 0 or nx >= M or visited[ny][nx] == 1:
            return False
    return True


def backtracking(y, x, score):
    global answer, visited
    if x == M:
        y += 1
        x = 0
    if y == N:
        answer = max(answer, score)
        return
    for block in blocks:
        if is_possible(y, x, block):
            curScore = 0
            for i in range(len(block)):
                ny = y + block[i][0]
                nx = x + block[i][1]
                curScore += board[ny][nx]
                visited[ny][nx] = 1
                if i == 0:
                    curScore += board[ny][nx]
            backtracking(y, x + 1, score + curScore)
            for i in range(len(block)):
                ny = y + block[i][0]
                nx = x + block[i][1]
                visited[ny][nx] = 0
    backtracking(y, x + 1, score)


N, M = map(int, input().split())
board = []
for _ in range(N):
    board.append(list(map(int, input().split())))
blocks = [[(0, 1), (0, 0), (1, 1)],
          [(1, 1), (0, 1), (1, 0)],
          [(1, 0), (0, 0), (1, 1)],
          [(0, 0), (0, 1), (1, 0)]]
visited = [[0] * M for _ in range(N)]
answer = 0
backtracking(0, 0, 0)
print(answer)



