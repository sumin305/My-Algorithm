import Foundation

func solution(_ operations:[String]) -> [Int] {
    var queue: [Int] = []
    for operation in operations {
        let n = operation.split(separator: " ")
        let (op, num) = (n[0], Int(n[1])!)
        if op == "I" {
            queue.append(num)
        }
        else if op == "D" && num == 1 {
            if !queue.isEmpty {
                queue.sort{$0 < $1}
                queue.removeLast()
            }
        }
        else {
            if !queue.isEmpty {
                queue.sort{$0 > $1}
                queue.removeLast()
            }
        }
    }
    return queue.isEmpty ? [0,0] : [queue.max()!, queue.min()!]
}