import Foundation

func solution(_ numbers:[Int], _ target:Int) -> Int {
    var result = 0
    func DFS(idx: Int, num: Int) {
        if idx == numbers.count {
            if num == target {
                result += 1
            }
            return
        }
        DFS(idx: idx+1, num: num + numbers[idx])
        DFS(idx: idx+1, num: num - numbers[idx])
    }
    DFS(idx: 0, num: 0)
    return result
}