import Foundation

func solution(_ jobs:[[Int]]) -> Int {
    var sortedByStartTime = jobs.sorted{
        if $0[0] == $1[0] { return $0[1] > $1[1]}
        else { return $0[0] > $1[0]}
    }
    print(sortedByStartTime)
    var waitQueue: [[Int]] = [sortedByStartTime.removeLast()]
    var time = waitQueue[0][0]
    var answer = 0
    
    while !waitQueue.isEmpty {
        let target = waitQueue.removeLast()
        answer += (time+target[1]) - target[0]
        time += target[1]
        
        print(sortedByStartTime)
        
        while !sortedByStartTime.isEmpty && sortedByStartTime.last![0] <= time {
            waitQueue.append(sortedByStartTime.removeLast())
        }
        waitQueue.sort(by: { $0[1] > $1[1] })
        // for task in sortedByStartTime {
        //     if task[0] <= time {
        //         waitQueue.append(task)
        //     } else {
        //         continue
        //     }
        // }
        
        if waitQueue.isEmpty {
            if !sortedByStartTime.isEmpty {
                waitQueue.append(sortedByStartTime.removeLast())
                time += waitQueue[0][0] - time
            }
        }
    }
    
    return answer / jobs.count
}