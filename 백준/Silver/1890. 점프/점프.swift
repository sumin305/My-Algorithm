import Foundation

let N = Int(readLine()!)!
var arr: [[Int]] = []
var dp: [[Int]] = Array(repeating: Array(repeating: 0, count: N), count: N)
for _ in 0..<N {
    arr.append(readLine()!.split(separator: " ").map{Int(String($0))!})
}

func isRange(_ x: Int, _ y: Int) -> Bool {
    if 0..<N ~= x && 0..<N ~= y {
        return true
    } else {
        return false
    }
}

dp[0][0] = 1
for i in 0..<N {
    for j in 0..<N {
        if i == N-1 && j == N-1 {
            break
        }
        let n1 = j + arr[i][j] // 오른쪽으로 이동
        let n2 = i + arr[i][j] // 아래로 이동
        if isRange(i,n1) {
            dp[i][n1] += dp[i][j]
        }
        if isRange(n2, j) {
            dp[n2][j] += dp[i][j]
        }
    }
}
print(dp.flatMap{$0}[dp.flatMap{$0}.endIndex-1])