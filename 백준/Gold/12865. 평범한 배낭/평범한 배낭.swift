import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N,K) = (n[0], n[1])
var dp : [[Int]] = Array(repeating : Array(repeating: 0, count: K+1), count:N+1)
for i in 1...N {
    let m = readLine()!.split(separator: " ").map{Int(String($0))!}
    let (weight,value) = (m[0],m[1])
    for j in 1...K{
        if weight>j{
            dp[i][j] = dp[i-1][j]
        }
        else{
            dp[i][j] = max(dp[i-1][j],dp[i-1][j-weight]+value)
        }
    }
    
}
print(dp[N][K])

