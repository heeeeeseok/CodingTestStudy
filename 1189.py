def backtracking(y, x, distance):
    global visited, answer
    if distance > K:
        return
    if y == 0 and x == C - 1 and distance == K:
        answer += 1
        return
    for dy, dx in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
        ny = y + dy
        nx = x + dx
        if 0 <= ny < R and 0 <= nx < C and not visited[ny][nx] and board[ny][nx] == '.':
            visited[ny][nx] = 1
            backtracking(ny, nx, distance + 1)
            visited[ny][nx] = 0


R, C, K = map(int, input().split())
visited = [[0] * C for _ in range(R)]
answer = 0
board = []
for _ in range(R):
    board.append(list(input().rstrip()))

visited[R - 1][0] = 1
backtracking(R - 1, 0, 1)
print(answer)
