import Foundation

let N = Int(readLine()!)!
var arr: [[Int]] = Array(repeating: [], count: N)
for i in 0..<N {
    arr[i] = Array(readLine()!.split(separator: " ").map{Int(String($0))!})
}
var whiteCount = 0
var blueCount = 0
func cutConfetti(_ tempArr: [[Int]], _ size: Int) {
    if size == 1 {
        if tempArr[0][0] == 0 {
            whiteCount += 1
        } else {
            blueCount += 1
        }
        return
    }
    
    let tempArrFlat = tempArr.flatMap{$0}
    
    if tempArrFlat.reduce(1){$0 * $1} == 1 {
        blueCount += 1
        return
    }
    if tempArrFlat.reduce(0){$0 + $1} == 0 {
        whiteCount += 1
        return
    }
    cutConfetti(splitConfetti(tempArr, (0, size/2), (0, size/2)), size/2)
    cutConfetti(splitConfetti(tempArr, (0, size/2), (size/2, size)), size/2)
    cutConfetti(splitConfetti(tempArr, (size/2, size), (0, size/2)), size/2)
    cutConfetti(splitConfetti(tempArr, (size/2, size), (size/2, size)), size/2)
}

func splitConfetti(_ tempArr: [[Int]], _ width: (Int, Int), _ height: (Int, Int)) -> [[Int]] {
    var arr: [[Int]] = []
    for idx in height.0..<height.1 {
        arr.append(Array(tempArr[idx][width.0..<width.1]))
    }
    return arr
}
cutConfetti(arr, N)
print(whiteCount)
print(blueCount)