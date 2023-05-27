import Foundation

let N = Int(readLine()!)!

var arr: [[Int]] = Array(repeating: [], count: N)
 
for i in 0..<N {
    arr[i] = Array(readLine()!).map{Int(String($0))!}
}
var result: [String] = []

func makeQuadTree(_ arr: [[Int]], _ n: Int) {
    if n == 1 {
        result.append(String(arr[0][0]))
        return
    }
    let tempArr = arr.flatMap{$0}
    
    if tempArr.reduce(0) {$0 + $1} == 0 {
        result.append(String(0))
        return
    }
    if tempArr.reduce(1) {$0 * $1} == 1 {
        result.append(String(1))
        return
    }
    result.append("(")
    makeQuadTree(splitConfetti(arr, (0, n/2), (0, n/2)), n/2)
    makeQuadTree(splitConfetti(arr, (n/2, n), (0, n/2)), n/2)
    makeQuadTree(splitConfetti(arr, (0, n/2), (n/2, n)), n/2)
    makeQuadTree(splitConfetti(arr, (n/2, n), (n/2, n)), n/2)
    result.append(")")
}
func splitConfetti(_ tempArr: [[Int]], _ width: (Int, Int), _ height: (Int, Int)) -> [[Int]] {
    var arr: [[Int]] = []
    for idx in height.0..<height.1 {
        arr.append(Array(tempArr[idx][width.0..<width.1]))
    }
    return arr
}
makeQuadTree(arr, N)
print(result.joined())