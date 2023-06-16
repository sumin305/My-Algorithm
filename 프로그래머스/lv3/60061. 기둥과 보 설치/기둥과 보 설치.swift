import Foundation

func solution(_ n:Int, _ build_frame:[[Int]]) -> [[Int]] {
    var result: [[Int]] = []
    
    func canInstall(_ x: Int, _ y: Int, _ isBeam: Int) -> Bool {
        // 기둥인 경우
        if isBeam == 0 {
            if (y == 0) { return true }
            if result.contains([x-1, y, 1]) { return true }
            if result.contains([x, y, 1]) { return true }
            if result.contains([x, y-1, 0]) { return true }
        }
        // 보인 경우
        else {
            if result.contains([x, y-1, 0]) { return true }
            if result.contains([x+1, y-1, 0]) { return true }
            if result.contains([x-1, y, 1]) && result.contains([x+1, y, 1]) { return true }
        }
        return false
    }
    
    func canRemove(_ x: Int, _ y: Int, _ isBeam: Int) -> Bool {

        result = result.filter{$0 != [x, y, isBeam]}
        // 기둥인 경우
        if isBeam == 0 {
            if result.contains([x, y+1, 1]) && !canInstall(x, y+1, 1) { return false }
            if result.contains([x, y+1, 0]) && !canInstall(x, y+1, 0) { return false }
            if result.contains([x-1, y+1, 0]) && !canInstall(x-1, y+1, 0) { return false }
        }
        // 보인 경우
        else {
            if result.contains([x, y, 1]) && !canInstall(x, y, 1) { return false }
            if result.contains([x+1, y, 1]) && !canInstall(x+1, y, 1) { return false }
            if result.contains([x-1, y, 1]) && !canInstall(x-1, y, 1) { return false }
            if result.contains([x+1, y, 1]) && !canInstall(x+1, y, 1) { return false }
        }
        return true
    }
    
    for frame in build_frame {
        let (x, y, a, b) = (frame[0], frame[1], frame[2], frame[3])
        // 삭제일 경우
        if b == 0 {
            if !canRemove(x, y, a) {
                result.append([x, y, a])
            }
        }
        // 설치일 경우
        if b == 1 {
            if canInstall(x, y, a) {
                result.append([x, y, a])
            }
        }
    }
    let sortedResult = result.sorted { (prev, next) -> Bool in
        if prev[0] != next[0] {
            return prev[0] < next[0]
        } else if prev[1] != next[1] {
            return prev[1] < next[1]
        } else {
            return prev[2] < next[2]
        }}
    return sortedResult
}