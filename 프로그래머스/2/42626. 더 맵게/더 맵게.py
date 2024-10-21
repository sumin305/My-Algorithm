import heapq
def solution(scoville, K):
    count = 0
    heapq.heapify(scoville)
    while scoville:
        a = heapq.heappop(scoville)
        if a < K and scoville:
            b = heapq.heappop(scoville)
            heapq.heappush(scoville, a + b*2)
            count += 1
        elif a >= K:
            return count
    return -1
            
            