"""
1 ~ n까지의 서로 다른 5개의 정수, 오름차순
q: 시도
ans: 시도별 포함된 숫자 개수
"""
from itertools import combinations


def solution(n, q, ans):
    answer = 0
    CODE_LENGTH = 5
    allCases = list(combinations([i + 1 for i in range(n)], CODE_LENGTH))
    for case in allCases:
        falseFlag = False
        for i in range(len(q)):
            count = 0
            for j in range(CODE_LENGTH):
                if q[i][j] in case:
                    count += 1
            if count != ans[i]:
                falseFlag = True
                break
        if not falseFlag:
            answer += 1

    return answer