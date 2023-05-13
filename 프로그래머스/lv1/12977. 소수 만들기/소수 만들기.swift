import Foundation


func isPrimeNum(_ num: Int) -> Bool {
    var i = 2
    while i < num {
        if num % i == 0 {
            return false
        }
        i += 1
    }
    return true
}

func solution(_ nums:[Int]) -> Int {
    var answer = 0
   for i in 0 ..< nums.count - 2 {
    for j in i + 1 ..< nums.count - 1 {
        for k in j + 1 ..< nums.count {
            if isPrimeNum(nums[i] + nums[j] + nums[k]) { answer += 1 }
        }
     }
    }
    return answer
}