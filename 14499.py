"""
좌표: (r, c)
초기 주사위의 눈은 모두 0
주사위를 굴렸을 때에 위치한 칸이 0이면 바닥면에 있는 수가 칸에 복사된다
0이 아니면 칸에 쓰여있는 수가 주사위 바닥면으로 복사되고 칸에 쓰인 수는 0이된다
상단에 쓰여있는 값을 답으로 출력
주사위가 지도 밖으로 나가면 해당 명령을 무시

명령어: 동(1), 서(2), 북(3), 남(4)
"""
from collections import deque


def isValid(N, M, x, y):
    return 0 <= x <= N - 1 and 0 <= y <= M - 1


# 지도세로, 지도가로, 주사위세로, 주사위가로, 명령 개수
N, M, x, y, K = map(int, input().split())
board = []
diceX = deque([0] * 4)  # 주사위 세로
diceY = deque([0] * 4)  # 주사위 가로
for _ in range(N):
    board.append(list(map(int, input().split())))
commands = list(map(int, input().split()))
for command in commands:
    if command == 1:  # 동
        if not isValid(N, M, x, y + 1):
            continue
        y += 1
        num = diceY.popleft()
        diceY.append(num)
        newX = deque([diceY[0], diceX[1], diceY[2], diceX[3]])
        diceX = newX
    elif command == 2:  # 서
        if not isValid(N, M, x, y - 1):
            continue
        y -= 1
        num = diceY.pop()
        diceY.appendleft(num)
        newX = deque([diceY[0], diceX[1], diceY[2], diceX[3]])
        diceX = newX
    elif command == 3:  # 북
        if not isValid(N, M, x - 1, y):
            continue
        x -= 1
        num = diceX.pop()
        diceX.appendleft(num)
        newY = deque([diceX[0], diceY[1], diceX[2], diceY[3]])
        diceY = newY
    else:  # 남
        if not isValid(N, M, x + 1, y):
            continue
        x += 1
        num = diceX.popleft()
        diceX.append(num)
        newY = deque([diceX[0], diceY[1], diceX[2], diceY[3]])
        diceY = newY
    # 바닥면에 있는 수를 칸에 복사
    if board[x][y] == 0:
        board[x][y] = diceX[0]
    # 칸에 쓰여있는 수가 주사위 바닥면으로 복사되고 칸에 쓰인 수는 0이된다
    else:
        diceX[0] = board[x][y]
        diceY[0] = board[x][y]
        board[x][y] = 0
    print(diceX[2])
