def get_10_from_26(n26):
    result = 0
    power = 1
    for i in range(len(n26) - 1, -1, -1):
        # a = 1 ~ z = 26
        result += int(ord(n26[i]) - 96) * power
        power *= 26
    return result


def get_26_from_10(n10):
    result = ''
    power = 26
    while n10 // power:
        q = n10 // power
        r = n10 % power
        n10 = q
        if r == 0:
            result = 'z' + result
            n10 -= 1
        else:
            result = chr(r + 96) + result
    r = n10 % power
    if r != 0:
        result = chr(r + 96) + result
    return result


def solution(n, bans):
    bans_to_10 = []
    for ban in bans:
        bans_to_10.append(get_10_from_26(ban))
    bans_to_10.sort()

    for num in bans_to_10:
        if n >= num:
            n += 1
        else:
            break
    return get_26_from_10(n)