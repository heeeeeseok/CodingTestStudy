"""
맥주 한 박스에 20개
50미터에 한 병씩 마신다
최대 20병을 들고다닐 수 있다
최대 1000미터 이동 가능
"""
from collections import defaultdict
from collections import deque

t = int(input())
for _ in range(t):
    n = int(input())
    positions = []
    visited = defaultdict(int)
    dq = deque()
    for _ in range(n + 2):
        x, y = map(int, input().split())
        positions.append((x, y))
    sx, sy = positions[0]  # 집
    ex, ey = positions[-1]  # 페스티벌
    dq.append((sx, sy))
    happyFlag = False
    # 방문처리
    while dq:
        cx, cy = dq.pop()
        # 페스티벌에 도달
        if cx == ex and cy == ey:
            happyFlag = True
            break
        visited[(cx, cy)] = 1
        for nx, ny in positions:
            # 방문하지 않았고 20병으로 도달 가능
            if not visited[(nx, ny)] and abs(cx - nx) + abs(cy - ny) <= 1000:
                dq.append((nx, ny))
    if happyFlag:
        print("happy")
    else:
        print("sad")


