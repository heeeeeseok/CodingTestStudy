"""
문자열 뒤에 A를 추가한다
문자열을 뒤집고 뒤에 B를 추가한다

T -> S
문자열 맨 뒤의 A를 뺀다
맨 뒤의 B를 빼고 문자열을 뒤집는다
"""

S = input()
T = input()
while len(S) < len(T) and S != T:
    if T[-1] == 'A':
        T = T[:-1]
    else:
        T = T[:-1]
        T = T[::-1]
print(1 if S == T else 0)
