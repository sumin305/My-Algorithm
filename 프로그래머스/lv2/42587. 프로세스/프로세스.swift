import Foundation

func solution(_ priorities:[Int], _ location:Int) -> Int {
    var queue: [(idx: Int, prior: Int)] = []
    var result = 0
    for i in 0..<priorities.count {
        queue.append((i, priorities[i]))
    }
    while !queue.isEmpty {
        let target = queue.removeFirst()
        print("target : \(target)")
        if queue.count == 0 {
            result += 1
            if target.idx == location {
                return result
            }
        }
        for i in 0..<queue.count {
            if queue[i].prior > target.prior {
                queue.append(target)
                break
            }
            if i == queue.count-1 {
                result += 1
                if target.idx == location {
                    return result
                }
            }
        }
    }
    return result    
}