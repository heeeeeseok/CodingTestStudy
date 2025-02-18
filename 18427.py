MAX_HEIGHT = 1001


N, M, H = map(int, input().split())
blocks = []
for _ in range(N):
    blocks.append(list(map(int, input().split())))
for block in blocks:
    block.append(0)
    block.sort()

answer = 0
dp = [[0] * MAX_HEIGHT for _ in range(N)]
for h in blocks[0]:
    dp[0][h] = 1
for i in range(1, N):
    for h in range(MAX_HEIGHT):
        for ch in blocks[i]:
            if h - ch >= 0:
                dp[i][h] += dp[i - 1][h - ch]
print(dp[-1][H] % 10007)
