from collections import deque
from functools import reduce
def solution(queue1, queue2):
    sum = reduce(lambda x, y: x + y, queue1) + reduce(lambda x, y: x + y, queue2)
    queue1 = deque(queue1)
    queue2 = deque(queue2)
    sum1 =  reduce(lambda x, y: x + y, queue1)
    sum2 = reduce(lambda x, y: x + y, queue2)
    
    count = 0
    if sum1 == sum2: return 0
    while count <= 2 * (len(queue1) + len(queue2)):
        if sum1 == sum2:
            return count
        if sum1 < sum // 2:
            target = queue2.popleft()
            queue1.append(target)
            sum1 += target
            sum2 -= target
        elif sum2 < sum // 2:
            target = queue1.popleft()
            queue2.append(target)
            sum1 -= target 
            sum2 += target
        count += 1
    return -1