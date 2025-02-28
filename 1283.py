N = int(input())
options = []
commands = set()
for _ in range(N):
    words = list(input().split())
    options.append(words)
    flag = False
    # 1 각 단어의 첫 글자를 단축키로 지정
    for i, word in enumerate(words):
        if word[0].lower() not in commands:
            commands.add(word[0].lower())
            words[i] = f"[{word[0]}]{word[1:]}"
            flag = True
            break
    # 2 모든 단어의 첫 글자가 이미 지정되어있는 경우
    if not flag:
        for i, word in enumerate(words):
            for j, c in enumerate(word):
                if c.lower() not in commands:
                    commands.add(c.lower())
                    words[i] = word[:j] + f"[{c}]" + word[j + 1:]
                    flag = True
                    break
            if flag:
                break
    print(" ".join(words))



