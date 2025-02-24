from collections import deque


def solution(storage, requests):
    n = len(storage)  # 세로
    m = len(storage[0])  # 가로
    answer = n * m
    board = [["-1"] * (m + 2) for _ in range(n + 2)]  # 상하좌우 한줄씩 추가
    for y in range(n):
        row = list(storage[y].rstrip())
        for x in range(m):
            board[y + 1][x + 1] = row[x]
    for r in requests:
        # 지게차
        if len(r) == 1:
            rpos = []
            for y in range(1, n + 1):
                for x in range(1, m + 1):
                    if board[y][x] == r:
                        for dy, dx in [(-1, 0), (1, 0), (0, 1), (0, -1)]:
                            ny = y + dy
                            nx = x + dx
                            if board[ny][nx] == "-1":
                                rpos.append((y, x))
                                answer -= 1
                                break
            for ry, rx in rpos:
                board[ry][rx] = "-1"
        # 크레인
        else:
            for y in range(1, n + 1):
                for x in range(1, m + 1):
                    if board[y][x] == r[0]:
                        board[y][x] = "0"
                        answer -= 1
        # 외부와 연결된 좌표 업데이트
        dq = deque()
        dq.append((0, 0))
        visited = [[0] * (m + 2) for _ in range(n + 2)]
        visited[0][0] = 1
        while dq:
            y, x = dq.popleft()
            for dy, dx in [(-1, 0), (1, 0), (0, 1), (0, -1)]:
                ny = y + dy
                nx = x + dx
                if 0 <= ny < n + 2 and 0 <= nx < m + 2 and not visited[ny][nx]:
                    if board[ny][nx] == "-1" or board[ny][nx] == "0":
                        board[ny][nx] = "-1"
                        dq.append((ny, nx))
                        visited[ny][nx] = 1
    return answer