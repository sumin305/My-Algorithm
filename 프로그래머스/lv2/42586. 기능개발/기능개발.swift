import Foundation

func solution(_ progresses:[Int], _ speeds:[Int]) -> [Int] {
    // 기능의 개발속도는 모두 다름
    // 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있음
    // 뒤에 있는 기능은 앞의 기능이 배포될 때 함께 배포
    // prgresses : 배포되어야 하는 순서대로 작업의 진도가 적힘 -> 이전에 적힌 작업이 배포되어야 이 후 작업 배포 가능
    // speeds : 작업의 개발 속도 -> 하루에 작업할 수 있는 양
    // 각 배포마다 몇 개의 기능이 배포되는지를 return
    
    // 1일 후 94 60 60
    // 2일 후 95 90 65
    // 3일 후 96 100 70
    // 4일 후 97 100 75
    // 5일 후 98 100 80
    // 6일 후 99 100 85
    // 7일 후 100 100 90 -> 2개 배포
    // 8일 후 95
    // 9일 후 100 -> 1개 배포
    
    // speed를 계산해줘야 하므로 idx를 기억해야함.
    
    //var progresses = progresses
    var queue:[(prog: Int, idx:Int)] = []
    var result:[Int] = []
    for idx in 0..<progresses.count {
        queue.append((progresses[idx], idx))
    }
    print(queue)
    
    while !queue.isEmpty {
        for i in 0..<queue.count {
            queue[i].prog += speeds[queue[i].idx]
        }
        if queue[0].prog >= 100 {
            var count = 0
            while !queue.isEmpty && queue[0].prog >= 100 {
                queue.removeFirst()
                count += 1
            }
            result.append(count)
        }
    }
    return result
}