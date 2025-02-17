s = input()
a = s.count('a')
s += s[:a - 1]  # 원형 문자열 처리
answer = float('inf')
for i in range(len(s) - (a - 1)):
    answer = min(answer, s[i:i + a].count('b'))
print(answer)
