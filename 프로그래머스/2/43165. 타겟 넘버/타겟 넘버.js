let nums = [];
let targetNumber = 0;
let count = 0;

function solution(numbers, target) {
    var answer = 0;
    nums = numbers;
    targetNumber = target;

    dfs(0, 0);
    return count;
}

function dfs(idx, sum) {
    if (idx == nums.length) {
        if (sum == targetNumber) {
            count++;
        }
        return;
    }

    dfs(idx + 1, sum + (-1 * nums[idx]));
    dfs(idx + 1, sum + (nums[idx]));
}