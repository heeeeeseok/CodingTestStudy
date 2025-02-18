"""
3개의 장애물 설치하여 모두 감시를 피할 수 있는지 계산
"""
def check():
    for ty, tx in tPos:
        for x in range(tx + 1, N):
            if board[ty][x] == 'O' or board[ty][x] == 'T':
                break
            elif board[ty][x] == 'S':
                return False
        for x in range(tx - 1, -1, -1):
            if board[ty][x] == 'O' or board[ty][x] == 'T':
                break
            elif board[ty][x] == 'S':
                return False
        for y in range(ty + 1, N):
            if board[y][tx] == 'O' or board[y][tx] == 'T':
                break
            elif board[y][tx] == 'S':
                return False
        for y in range(ty - 1, -1, -1):
            if board[y][tx] == 'O' or board[y][tx] == 'T':
                break
            elif board[y][tx] == 'S':
                return False
    return True


N = int(input())
board = []
for _ in range(N):
    board.append(list(input().split()))

xPos = []
tPos = []
for i in range(N):
    for j in range(N):
        if board[i][j] == 'X':
            xPos.append((i, j))
        elif board[i][j] == 'T':
            tPos.append((i, j))

for i in range(len(xPos) - 2):
    for j in range(i + 1, len(xPos) - 1):
        for k in range(j + 1, len(xPos)):
            y1, x1 = xPos[i]
            y2, x2 = xPos[j]
            y3, x3 = xPos[k]
            board[y1][x1] = 'O'
            board[y2][x2] = 'O'
            board[y3][x3] = 'O'
            if check():
                print('YES')
                exit()
            board[y1][x1] = 'X'
            board[y2][x2] = 'X'
            board[y3][x3] = 'X'
print('NO')
