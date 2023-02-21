import Foundation

let N = readLine()!.split(separator: " ").map{Int(String($0))!}
let (n,k) = (N[0], N[1])
var dp : [Int] = Array(repeating: 10001, count: k+1)
var num : [Int] = []
dp[0] = 0
for i in 0..<n {
    let a = Int(readLine()!)!
    num.append(a)
}
num.sorted(by: <)
for i in 1...k {
    for j in num {
        if i < j {
            continue
        }
        dp[i] = min(dp[i-j]+1, dp[i])
        }
    }

if dp[k] == 10001 {
    print(-1)
}
else {
    print(dp[k])
}
