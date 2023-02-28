import Foundation

let N = Int(readLine()!)!
var arr : [[Int]] = []

//감소하는 수
//한자리
//0 1 2 3 4 5 6 7 8 9
//두자리
//10 20 21 30 31 32 40 41 42..
//세자리
//210 310 320 321 410 420 421
arr.append([0,1,2,3,4,5,6,7,8,9])
var count = 9
var i = 0
while count <= N {
    var minArr : [Int] = []
    for j in arr[i] {
        let str = String(j)
        let num = Int(String(str[str.startIndex]))!
        if num < 9 {
            for k in num+1...9 {
                minArr.append(Int(String(k) + String(j))!)
            }
        }
    }
    if minArr.isEmpty {
        break
    }
    i += 1
    arr.append(minArr.sorted(by: <))
    count += minArr.count
}

if N > 1022 {
    print(-1)
}
else {
    print(arr.flatMap({$0})[N])
}

