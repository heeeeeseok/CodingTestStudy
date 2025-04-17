def cal(x1, y1, x2, y2):
    return abs(x1 - x2) ** 2 + abs(y1 - y2) ** 2


def solution(m, n, startX, startY, balls):
    answer = []
    for destX, destY in balls:
        result = 2000000
        
        # 상단 쿠션
        if startX != destX or destY < startY: 
            newY = n + (n - destY)
            result = min(result, cal(startX, startY, destX, newY))
        
        # 하단 쿠션
        if startX != destX or destY > startY: 
            newY = -destY
            result = min(result, cal(startX, startY, destX, newY))
        
        # 왼쪽 쿠션
        if startY != destY or destX > startX:
            newX = -destX
            result = min(result, cal(startX, startY, newX, destY))
        
        # 오른쪽 쿠션
        if startY != destY or destX < startX:
            newX = m + (m - destX)
            result = min(result, cal(startX, startY, newX, destY))
        
        answer.append(result)
        
    return answer