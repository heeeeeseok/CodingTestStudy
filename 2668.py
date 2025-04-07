from collections import deque


n = int(input())
nums = [-1]
for i in range(n):
    nums.append(int(input()))

sameNums = []
for i in range(1, n + 1):
    if i == nums[i]:
        sameNums.append(i)

resultNums = []
dq = deque()
for i in range(1, n + 1):
    if (i != nums[i]):
        visited = [0] * (n + 1)
        visited[i] = 1
        dq.append([i, nums[i], 1])
        while dq:
            target, next, count = dq.pop()
            visited[next] = 1
            if (nums[next] == target):
                curResult = []
                for j in range(1, n + 1):
                    if visited[j]:
                        curResult.append(j)
                if curResult not in resultNums:
                    resultNums.append(curResult)
                break
            elif (nums[next] == next or visited[nums[next]]):
                break
            else:
                dq.append([target, nums[next], count + 1])

result = []
for rNums in resultNums:
    for elem in rNums:
        result.append(elem)

print(len(sameNums) + len(result))
for elem in sameNums:
    result.append(elem)
result.sort()
for num in result:
    print(num)
