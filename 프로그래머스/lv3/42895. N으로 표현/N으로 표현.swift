import Foundation

func solution(_ N:Int, _ number:Int) -> Int {
    var dp : [[Int]] = Array(repeating: [], count: 9)
    dp[1] = [N]
    if dp[1].contains(number) {
        return 1
    }
    for i in 2...8 {
        var setArr: Set<Int> = []
        setArr.insert(Int(String(repeating: String(N), count: i))!)
        for j in 1...i-1 {
            // dp[j], dp[i-j]의 사칙연산
            for k in dp[j] {
                for l in dp[i-j] {
                    setArr.insert(k + l)
                    setArr.insert(k - l)
                    setArr.insert(l - k)
                    setArr.insert(k * l)
                    if l != 0 {
                        setArr.insert(k / l)
                    }
                    if k != 0 {
                        setArr.insert(l / k)
                    }
                }
            }
        }
        dp[i] = Array(setArr)
        if dp[i].contains(number) {
            return (i)
        }
    }
    return -1
}