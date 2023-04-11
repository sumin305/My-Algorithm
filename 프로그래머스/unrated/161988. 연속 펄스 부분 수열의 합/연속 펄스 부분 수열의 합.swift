import Foundation

func solution(_ sequence:[Int]) -> Int64 {
    let arr1 = sequence.enumerated().map{ $0.offset % 2 == 0 ? Int64(-$0.element) : Int64($0.element)}
    let arr2 = sequence.enumerated().map{ $0.offset % 2 != 0 ? Int64(-$0.element) : Int64($0.element)}
    var dp1: [Int64] = Array(repeating: 0, count: sequence.count)
    var dp2: [Int64] = Array(repeating: 0, count: sequence.count)
    var result1: Int64 = arr1[0]
    var result2: Int64 = arr2[0]
    dp1[0] = arr1[0]
    dp2[0] = arr2[0]
    for i in 1..<sequence.count {
        dp1[i] = max(dp1[i-1] + arr1[i], arr1[i])
        dp2[i] = max(dp2[i-1] + arr2[i], arr2[i])
        result1 = max(result1, dp1[i])
        result2 = max(result2, dp2[i])
    }
    return max(result1, result2)
}