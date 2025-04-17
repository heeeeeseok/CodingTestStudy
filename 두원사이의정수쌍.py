import math


def solution(r1, r2):
    answer = 0
    
    for y in range(0, r2):
        x1 = max(0, r1**2 - y**2)
        x2 = r2**2 - y**2
        if math.ceil(math.sqrt(x1)) == 0:
            answer -= 1
        answer += (math.floor(math.sqrt(x2)) - math.ceil(math.sqrt(x1)) + 1)

    return answer * 4