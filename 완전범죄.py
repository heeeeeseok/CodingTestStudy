"""
a가 i를 훔치면 info[i][0]개의 흔적
b가 i를 훔치면 info[i][1]개의 흔적

a는 흔적이 n개 이상이면 안됨
b는 흔적이 m개 이상이면 안됨

모든 물건을 훔쳤을 때 A의 최소 흔적을 리턴
모든 물건을 훔칠 수 없다면 -1을 리턴
"""
from collections import deque

def solution(info, n, m):
    info.insert(0, 0)
    # dp[i][b점수] = a의 최소
    dp = [[121] * 121 for _ in range(41)]
    dp[0][0] = 0
    for i in range(1, len(info)):
        for j in range(121):
            # i를 b가 훔치는 경우
            newB = j + info[i][1]
            if newB < m:
                dp[i][newB] = min(dp[i][newB], dp[i-1][j])
            # i를 a가 훔치는 경우
            newA = dp[i-1][j] + info[i][0]
            if newA < n:
                dp[i][j] = min(dp[i][j], newA)
    result = min(dp[len(info) - 1])
    return result if result != 121 else -1