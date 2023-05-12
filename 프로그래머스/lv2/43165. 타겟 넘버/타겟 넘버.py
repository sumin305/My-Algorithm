answer = 0
def DFS(nums, idx, sum, target):
    global answer
    if idx == len(nums) and sum == target:
        answer += 1
        return
    if idx == len(nums):
        return
    DFS(nums, idx+1, sum+nums[idx], target)
    DFS(nums, idx+1, sum-nums[idx], target)

def solution(numbers, target):
    global answer
    DFS(numbers, 0, 0, target)
    return answer
