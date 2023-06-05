import Foundation

func solution(_ cap:Int, _ n:Int, _ deliveries:[Int], _ pickups:[Int]) -> Int64 {
    var result = 0
    var del = deliveries
    var picks = pickups
    del.trimZeroSuffix()
    picks.trimZeroSuffix()
    
    while !del.isEmpty || !picks.isEmpty {
        result += max(del.count , picks.count) * 2
        var tempCap = cap
        while !del.isEmpty {
            if del.last! > tempCap {
                del[del.count - 1] -= tempCap
                break
            } else {
                tempCap -= del[del.count - 1]
                del.removeLast()
            }
        }
        tempCap = cap
        while !picks.isEmpty {
            if picks.last! > tempCap {
                picks[picks.count - 1] -= tempCap
                break
            } else {
                tempCap -= picks[picks.count - 1]
                picks.removeLast()
            }
        }
    }
    return Int64(result)
}

extension Array where Element == Int {
    mutating func trimZeroSuffix() {
        while self.last ?? 1 == 0 {
            self.removeLast()
        }
    }
}