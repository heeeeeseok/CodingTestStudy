N, L = map(int, input().split())
positions = []
for _ in range(N):
    s, e = map(int, input().split())
    positions.append((s, e))
positions.sort()
answer = 0
curPos = 0
for start, end in positions:
    curPos = max(curPos, start)
    q = ((end - curPos) // L)
    if (end - curPos) % L != 0:
        q += 1
    answer += q
    curPos += (q * L)
print(answer)
