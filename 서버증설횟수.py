"""
사람 m명당 서버 1대 필요
서버는 k시간 뒤에 사라짐
"""


def solution(players, m, k):
    total = 0
    cur = 0
    end_count = [0] * 25  # 반납할 서버의 시간과 수량
    for i in range(len(players)):
        cur -= end_count[i]  # 서버 반납
        q = players[i] // m
        # 서버 증설이 필요한 경우
        if cur < q:
            additional = q - cur
            cur += additional
            total += additional
            end_count[min(24, i + k)] += additional
    return total