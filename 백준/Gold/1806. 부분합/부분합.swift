import Foundation
let ns = readLine()!.split(separator: " ").map{Int(String($0))!}
let n = ns[0]
let s = ns[1]
var arr = readLine()!.split(separator: " ").map{Int(String($0))!}

var end = 0
var sum = 0
var count = 0
var result = Int.max
for start in 0..<n{
    while sum < s && end < n{
        sum += arr[end]
        end += 1
        count += 1
    }
    if sum >= s{
        result = min(result, count)
    }
    sum -= arr[start]
    count -= 1
}
print(result == Int.max ? 0 : result)