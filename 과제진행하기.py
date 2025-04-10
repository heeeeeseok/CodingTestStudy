from collections import deque


def solution(plans):
    answer = []
    waitingQueue = deque()
    unCompletedQueue = deque()
    
    for i in range(len(plans)):
        hour, min = plans[i][1].split(':')
        start = int(hour) * 60 + int(min)
        end = start + int(plans[i][2])
        plans[i] = [plans[i][0], start, end]
    plans.sort(key=lambda x: x[1])
    
    for plan in plans:
        # 아무런 작업이 없을 때
        if not waitingQueue and not unCompletedQueue:
            waitingQueue.append(plan)
            continue
                
        name, start, end = waitingQueue.popleft()
        nName, nStart, nEnd = plan
        
        # 새로운 작업 우선
        waitingQueue.append(plan)
        
        # 새로운 작업 이전에 종료됨
        if end <= nStart:
            answer.append(name)
            
            curTime = end
            # 도중에 종료된 작업이 존재
            while unCompletedQueue:
                nextName, remain = unCompletedQueue.popleft()
                if curTime + remain <= nStart:
                    answer.append(nextName)
                    curTime += remain
                else:
                    unCompletedQueue.appendleft([nextName, (curTime + remain) - nStart])
                    break
        # 도중에 종료됨
        else:
            unCompletedQueue.appendleft([name, end - nStart])
        
    answer.append(waitingQueue[0][0])
    while unCompletedQueue:
        name = unCompletedQueue.popleft()[0]
        answer.append(name)
    return answer