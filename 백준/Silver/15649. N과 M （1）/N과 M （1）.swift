import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}

let (N, M) = (n[0], n[1])

var result: [[Int]] = []

combi([], Array(1...N))
func combi( _ arr: [Int], _ nums: [Int]) {
    if arr.count == M {
        result.append(arr)
        return
    }
    for idx in 0..<nums.count {
        var tempArr = arr
        var tempNums = nums
        tempArr.append(nums[idx])
        tempNums.remove(at: idx)
        combi(tempArr, tempNums)
    }
}
for r in result {
    print(r.map{String($0)}.joined(separator: " "))
}