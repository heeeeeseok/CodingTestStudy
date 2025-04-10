def solution(picks, minerals):
    answer = 0
    diamondCost = 0
    ironCost = 0
    rockCost = 0
    costs = []
    
    for i in range(min(5 * (picks[0] + picks[1] + picks[2]), len(minerals))):
        if minerals[i] == 'diamond':
            diamondCost += 1
            ironCost += 5
            rockCost += 25
        elif minerals[i] == 'iron':
            diamondCost += 1
            ironCost += 1
            rockCost += 5
        else:
            diamondCost += 1
            ironCost += 1
            rockCost += 1
        
        if i % 5 == 4:
            print(i)
            costs.append([rockCost, ironCost, diamondCost])
            diamondCost = 0
            ironCost = 0
            rockCost = 0
    
    if diamondCost != 0 or ironCost != 0 or rockCost != 0:
        costs.append([rockCost, ironCost, diamondCost])
        
    costs.sort(key=lambda x: (-x[0], x[1]))
    
    idx = 0
    while picks[0] > 0 and idx < len(costs):
        answer += costs[idx][2]
        idx += 1
        picks[0] -= 1
    while picks[1] > 0 and idx < len(costs):
        answer += costs[idx][1]
        idx += 1
        picks[1] -= 1
    while picks[2] > 0 and idx < len(costs):
        answer += costs[idx][0]
        idx += 1
        picks[2] -= 1
    return answer