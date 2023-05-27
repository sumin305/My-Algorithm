import Foundation

let N = Int(readLine()!)!

var matrix: [[Int]] = Array(repeating: [], count: N)

for i in 0..<N {
    matrix[i] = Array(readLine()!.split(separator: " ").map{Int(String($0))!})
}
var count: [Int] = [0,0,0]
func check(_ arr: [[Int]]) -> Bool {
    let first = arr[0][0]
    let flatArr = arr.flatMap{$0}
    for a in flatArr {
        if a != first {
            return false
        }
    }
    return true
}
func countPaper(_ arr: [[Int]], _ n: Int) {
    if n == 1 {
        count[arr[0][0] + 1] += 1
        return
    }
        
    if check(arr) {
        count[arr[0][0] + 1] += 1
        return
    }
    
    if arr[0].count == 3 {
        for i in 0..<3 {
            for j in 0..<3 {
                count[arr[i][j]+1] += 1
            }
        }
        return
    }
    
    let tempNum = n / 3

    countPaper(divideMatrix(arr, width: (0, tempNum), height: (0, tempNum)), tempNum)
    countPaper(divideMatrix(arr, width: (tempNum,tempNum*2), height: (0, tempNum)), tempNum)
    countPaper(divideMatrix(arr, width: (tempNum*2, n), height: (0, tempNum)), tempNum)
    
    countPaper(divideMatrix(arr, width: (0, tempNum), height: (tempNum,tempNum*2)), tempNum)
    countPaper(divideMatrix(arr, width: (tempNum,tempNum*2), height: (tempNum,tempNum*2)), tempNum)
    countPaper(divideMatrix(arr, width: (tempNum*2, n), height: (tempNum,tempNum*2)), tempNum)
    
    countPaper(divideMatrix(arr, width: (0, tempNum), height: (tempNum*2, n)), tempNum)
    countPaper(divideMatrix(arr, width: (tempNum,tempNum*2), height: (tempNum*2, n)), tempNum)
    countPaper(divideMatrix(arr, width: (tempNum*2, n), height: (tempNum*2, n)), tempNum)
}

func divideMatrix(_ arr: [[Int]], width: (Int, Int), height: (Int, Int)) -> [[Int]] {
    var tempArr: [[Int]] = []
    for idx in height.0..<height.1 {
        tempArr.append(Array(arr[idx][width.0..<width.1]))
    }
    return tempArr
}
countPaper(matrix, N)
count.forEach{ print($0) }
