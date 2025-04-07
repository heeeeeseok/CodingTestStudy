def solution(h1, m1, s1, h2, m2, s2):
    # 초침: 1초에 6도
    # 분침: 1초에 0.1도
    # 시침: 1초에 1/120도
    answer = 0
    t1 = (h1 * 3600) + (m1 * 60) + s1
    t2 = (h2 * 3600) + (m2 * 60) + s2
    time = t2 - t1
    
    if t1 == 0 or t1 == 12 * 3600:
        answer += 1
        
    while time > 0:
        hd = t1 / 120 % 360
        md = t1 / 10 % 360
        sd = t1 * 6 % 360
        
        nhd = 360 if (t1 + 1) / 120 % 360 == 0 else (t1 + 1) / 120 % 360
        nmd = 360 if (t1 + 1) / 10 % 360 == 0 else (t1 + 1) / 10 % 360
        nsd = 360 if (t1 + 1) * 6 % 360 == 0 else (t1 + 1) * 6 % 360
        
        if sd < hd and nsd >= nhd:
            answer += 1
        if sd < md and nsd >= nmd:
            answer += 1
        if nsd == nhd and nhd == nmd:
            answer -= 1

        t1 += 1
        time -= 1
    
    return answer