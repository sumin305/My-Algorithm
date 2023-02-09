import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N,S) = (n[0], n[1])
let nums = readLine()!.split(separator: " ").map{Int(String($0))!}
var (left, right, temp, result) = (0, 0, 0, Int.max)

while true{
    if temp >= S{
        result = min(result, right - left)
        temp -= nums[left]
        left += 1
    }
    else if right == N{
        break
    }else{
        temp += nums[right]
        right += 1
    }
}

if result == Int.max{
    print(0)
}else{
    print(result)
}