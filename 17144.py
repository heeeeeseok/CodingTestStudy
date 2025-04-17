from copy import deepcopy


R, C, T = map(int, input().split())
board = []
machineTop = None
machineBottom = None
for i in range(R):
    line = list(map(int, input().split()))
    board.append(line)
    for j in range(C):
        if line[j] == -1:
            if machineTop is None:
                machineTop = (i, j)
            else:
                machineBottom = (i, j)

while T > 0:
    T -= 1
    newBoard = [[0]*C for _ in range(R)]
    # 1. 확산
    for i in range(R):
        for j in range(C):
            if board[i][j] > 0:
                amount = board[i][j] // 5
                count = 0
                for dy, dx in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                    ny = i + dy
                    nx = j + dx
                    if 0 <= ny < R and 0 <= nx < C and board[ny][nx] != -1:
                        newBoard[ny][nx] += amount
                        count += 1
                newBoard[i][j] += board[i][j] - amount * count
                diffusedAmount = board[i][j] // 5
            elif board[i][j] == -1:
                newBoard[i][j] == -1

    # 2. 이동 - 반시계
    curY, curX = machineTop
    next = 0
    while curX != C - 1:
        cur = next
        curX += 1
        next = newBoard[curY][curX]
        newBoard[curY][curX] = cur
    while curY != 0:
        cur = next
        curY -= 1
        next = newBoard[curY][curX]
        newBoard[curY][curX] = cur
    while curX != 0:
        cur = next
        curX -= 1
        next = newBoard[curY][curX]
        newBoard[curY][curX] = cur
    while curY != machineTop[0]:
        cur = next
        curY += 1
        next = newBoard[curY][curX]
        newBoard[curY][curX] = cur
    while curX != machineTop[1]:
        cur = next
        curX += 1
        next = newBoard[curY][curX]
        newBoard[curY][curX] = cur
    newBoard[machineTop[0]][machineTop[1]] = -1

    curY, curX = machineBottom
    next = 0
    while curX != C - 1:
        cur = next
        curX += 1
        next = newBoard[curY][curX]
        newBoard[curY][curX] = cur
    while curY != R - 1:
        cur = next
        curY += 1
        next = newBoard[curY][curX]
        newBoard[curY][curX] = cur
    while curX != 0:
        cur = next
        curX -= 1
        next = newBoard[curY][curX]
        newBoard[curY][curX] = cur
    while curY != machineBottom[0]:
        cur = next
        curY -= 1
        next = newBoard[curY][curX]
        newBoard[curY][curX] = cur
    while curX != machineBottom[1]:
        cur = next
        curX += 1
        next = newBoard[curY][curX]
        newBoard[curY][curX] = cur
    newBoard[machineBottom[0]][machineBottom[1]] = -1

    board = newBoard
answer = 0
for i in range(R):
    for j in range(C):
        if board[i][j] > 0:
            answer += board[i][j]
print(answer)

