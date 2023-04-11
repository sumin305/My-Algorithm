
import Foundation

func solution(_ sequence: [Int]) -> Int64 {
    let a = sequence.enumerated().map { $0.offset % 2 == 0 ? Int64(-$0.element) : Int64($0.element) }
    let b = sequence.enumerated().map { $0.offset % 2 == 0 ? Int64($0.element) : Int64(-$0.element) }
    var dpA: [Int64] = Array(repeating: 0, count: a.count)
    var dpB: [Int64] = Array(repeating: 0, count: b.count)
    var resultA: Int64 = a[0]
    var resultB: Int64 = b[0]
    dpA[0] = a[0]
    dpB[0] = b[0]

    for i in 1..<a.count {
        dpA[i] = max(a[i] + dpA[i - 1], a[i])
        dpB[i] = max(b[i] + dpB[i - 1], b[i])
        resultA = max(resultA, dpA[i])
        resultB = max(resultB, dpB[i])
    }

    return max(resultA, resultB)
}

