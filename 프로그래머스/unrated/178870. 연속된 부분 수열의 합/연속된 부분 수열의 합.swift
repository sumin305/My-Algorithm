import Foundation

func solution(_ sequence:[Int], _ k:Int) -> [Int] {
    var min = sequence.count+1
    var (start, end) = (0, 0)
    var result: [Int] = []
    var sum = sequence[0]
    while start < sequence.count {
        if sum < k {
            if end == sequence.count - 1 {
                break
            }
            end += 1
            sum += sequence[end]
        } else if sum > k {
            sum -= sequence[start]
            start += 1
        } else {
            if end - start + 1 < min {
                min = end - start + 1
                result = [start, end]
            }
            if end == sequence.count - 1 {
                break
            }
            end += 1
            sum += sequence[end]
        }
    }
   return result
}