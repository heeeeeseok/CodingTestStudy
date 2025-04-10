def solution(sequence, k):
    answer = []
    answerLen = 1000001
    left = 0
    right = 1
    prefixSum = [0] * (len(sequence) + 1)
    for i in range(len(sequence)):
        prefixSum[i + 1] = prefixSum[i] + sequence[i]
    
    while right <= len(sequence):
        curSum = prefixSum[right] - prefixSum[left]
        curLen = right - left
        if curSum == k and answerLen > curLen:
            answerLen = curLen
            answer = [left, right - 1]
        elif curSum < k:
            right += 1
        else:
            left += 1
    return answer