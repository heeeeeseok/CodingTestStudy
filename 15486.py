N = int(input())
jobs = [()]  # 1일부터 시작
for _ in range(N):
    t, p = map(int, input().split())
    jobs.append((t, p))
dp = [0] * (N + 1)
for i in range(1, len(jobs)):
    t, p = jobs[i]
    dp[i] = max(dp[i], dp[i - 1])
    if i + t - 1 <= N:
        dp[i + t - 1] = max(dp[i + t - 1], dp[i - 1] + p)
print(dp[-1])
