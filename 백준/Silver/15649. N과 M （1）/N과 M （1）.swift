import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}

let (N, M) = (n[0], n[1])


combi([], Array(1...N))

func combi( _ arr: [Int], _ nums: [Int]) {
    if arr.count == M {
        print(arr.map{String($0)}.joined(separator: " "))
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