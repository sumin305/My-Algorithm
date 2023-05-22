import Foundation

func solution(_ k:Int, _ dungeons:[[Int]]) -> Int {

    func combi(_ selected: [[Int]], _ candidates: [[Int]]) {
        if candidates.isEmpty {
            if canAdventure(selected) > maxCount {
                maxCount = canAdventure(selected)
            }
            return
        }
        for idx in 0..<candidates.count {
            var selected = selected
            var tempArr = candidates
            selected.append(candidates[idx])
            tempArr.remove(at: idx)
            combi(selected, tempArr)
        }
    }
    func canAdventure(_ dungeons: [[Int]]) -> Int {
        var k = k
        var count = 0
        for dungeon in dungeons {
            if dungeon[0] <= k {
                k -= dungeon[1]
                count += 1
            } else {
                return count
            }
        }
        return count
    }
    var maxCount = 0
    combi([], dungeons)
    
    return maxCount
}