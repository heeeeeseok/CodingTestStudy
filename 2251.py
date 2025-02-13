def dfs(a, b, c):
    if a == 0:
        answer.add(c)
    if visited.get((a, b, c), 1):
        visited[(a, b, c)] = 0
        if a != 0:
            # A에서 B로 물 붓기
            if b + a > B:
                dfs(a - (B - b), B, c)
            else:
                dfs(0, b + a, c)
            # A에서 C로 물 붓기
            if c + a > C:
                dfs(a - (C - c), b, C)
            else:
                dfs(0, b, c + a)
        if b != 0:
            # B에서 A로 물 붓기
            if a + b > A:
                dfs(A, b - (A - a), c)
            else:
                dfs(a + b, 0, c)
            # B에서 C로 물 붓기
            if c + b > C:
                dfs(a, b - (C - c), C)
            else:
                dfs(a, 0, b + c)
        if c != 0:
            # C에서 A로 물 붓기
            if a + c > A:
                dfs(A, b, c - (A - a))
            else:
                dfs(a + c, b, 0)
            # C에서 B로 물 붓기
            if b + c > B:
                dfs(a, B, c - (B - b))
            else:
                dfs(a, b + c, 0)


A, B, C = map(int, input().split())
answer = set()
visited = {}
dfs(0, 0, C)
result = list(answer)
result.sort()
print(*result)

