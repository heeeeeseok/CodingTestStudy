N, K = map(int, input().split())
# dp[N][K]
dp = [[0] * 201 for _ in range(201)]
dp[0][1] = 1
for i in range(201):
    dp[i][1] = 1
for i in range(201):
    for j in range(2, 201):
        for k in range(0, i + 1):
            dp[i][j] += dp[i - k][j - 1]

print(dp[N][K] % 1000000000)
