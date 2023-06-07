import Foundation

let a = Array(readLine()!.map{Int(String($0))!})
let b = Array(readLine()!.map{Int(String($0))!})

var long: [Int] = []
var short: [Int] = []

var idxUp = false
var isEnd = false
if a.count >= b.count {
    long = a
    short = b
} else {
    long = b
    short = a
}
func searchMinLength() -> Int {
    var minLength = Int.max
    var doubleLength = 1
    var idx = 0 // 맞물리는 부분이 시작되는 인덱스
    
    while idx < long.count {
        for i in 0..<doubleLength {
            if isEnd {
                if long[idx + doubleLength - i - 1] * short[doubleLength - i - 1] == 4 {
                    break
                }
            }
            else {
                if long[idx + doubleLength - i - 1] * short[short.count - 1 - i] == 4 {
                    break
                }
            }
            if i == doubleLength - 1 {
                minLength = min(minLength, short.count + long.count - doubleLength)
            }
        }
        // 긴 장치 앞부분부터 비교하는 경우
        if idx == 0 {
            doubleLength += 1
        }
        
        // 긴 장치안에 작은 장치가 완전히 들어왔을 경우
        if doubleLength == short.count+1 {
            idxUp = true
            doubleLength = short.count
        }
        if idxUp {
            idx += 1
        }
        
        // 긴 장치를 작은 장치가 나갔을 경우
        if long.count - idx < short.count {
            isEnd = true
            doubleLength -= 1
            if doubleLength < 1 {
                break
            }
        }
    }
    if minLength == Int.max {
        minLength = long.count + short.count
    }
    return minLength
}
print(searchMinLength())