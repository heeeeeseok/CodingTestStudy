"""
계란끼리 치게 되면 다른 계란의 무게만큼 내구도가 깎인다.

1. 가장 왼쪽 계란을 든다.
2. 깨지지 않은 다른 계란을 친다. 이때 손에 든 계란이 깨지거나 깨지지 않은 계란이 없으면 넘어간다.
3. 한 칸 오른쪽 계란을 들고 2번을 다시 진행한다. 오른쪽 끝에 도달하면 종료한다.

최대로 깰 수 있는 계란의 수
"""
def solve(idx):
    global eggs, result
    # 오른쪽 끝에 도달하면 깨진 계란 계수를 갱신하고 종료
    if idx == N:
        count = 0
        for s, w in eggs:
            if s <= 0:
                count += 1
        result = max(result, count)
        return
    s1, w1 = eggs[idx]
    # 손에 든 계란이 이미 깨진 경우 다음으로 진행
    if s1 <= 0:
        return solve(idx + 1)
    flag = True
    for i in range(N):
        # 손에 든 계란이 아니면서 깨지지 않은 경우
        if i != idx and eggs[i][0] > 0:
            flag = False
            s2, w2 = eggs[i]
            eggs[idx][0] -= w2
            eggs[i][0] -= w1
            solve(idx + 1)
            eggs[idx][0] = s1
            eggs[i][0] = s2
    if flag:
        return solve(idx + 1)


# 내구도
N = int(input())
eggs = []
result = 0
for i in range(N):
    # 내구도, 무게
    eggs.append(list(map(int, input().split())))
solve(0)
print(result)
