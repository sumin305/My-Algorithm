def solution(numbers, target):
    global answer
    answer = 0
    
    def dfs(sum, idx):
        global answer
        if idx == len(numbers):
            if sum == target:
                answer += 1
            return
        
        dfs(sum + numbers[idx], idx+1)
        dfs(sum - numbers[idx], idx+1)
        
    dfs(0, 0)
    return answer
