import Foundation

func solution(_ bridge_length:Int, _ weight:Int, _ truck_weights:[Int]) -> Int {
    var queue: [Int] = Array(repeating:0, count: bridge_length)
    var result = 0
    var queueWeight = 0
    var car = 0
    while car < truck_weights.count {
        result += 1
        let target = queue.removeFirst() 
        queueWeight -= target
        
        if truck_weights[car] + queueWeight <= weight {
            queue.append(truck_weights[car])
            queueWeight += truck_weights[car]
            car += 1
        } else {
            queue.append(0)
        }
    }
    result += bridge_length
    
    return result
}