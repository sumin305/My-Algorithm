import Foundation
let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (H,W) = (n[0], n[1])
let arr: [Int] = readLine()!.split(separator: " ").map{Int(String($0))!}
var result = 0
for j in 1...W-2{
    let left = (arr[..<j]).max()!
    let right = (arr[j...]).max()!
    let m = min(left, right)
    if arr[j] < m {
        result += m - arr[j]
    }
}
print(result)