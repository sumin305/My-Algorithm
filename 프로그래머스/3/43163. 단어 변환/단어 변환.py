from collections import deque

def solution(begin, target, words):
    answer = 0
    queue = deque([(begin, 0)])
    n = len(words[0])
    if not target in words: return 0

    def check(str1, str2, n):
        count = 0
        for i in range(n):
            if str1[i] != str2[i]: 
                count += 1
        if count == 1: 
            return True
        else: return False
            
    while queue:
        current_str, count = queue.popleft()
        if current_str == target: 
            return count
        for word in words:
            if word == current_str: continue
            if check(word, current_str, n): 
                queue.append((word, count+1))
                 
    return answer