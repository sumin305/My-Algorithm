import Foundation

let t = Int(readLine()!)!

for _ in 0..<t {
    if canHappy() {
        print("happy")
    } else {
        print("sad")
    }
}

func canHappy() -> Bool {
    let n = Int(readLine()!)!
    let sangen = readLine()!.split(separator: " ").map{Int(String($0))!}
    var (sangenX, sangenY) = (sangen[0], sangen[1])
    var locations: [(x: Int, y: Int)] = []
    for _ in 0...n {
        let location = readLine()!.split(separator: " ").map{Int(String($0))!}
        locations.append((location[0], location[1]))
    }
    
    var visited: [Bool] = Array(repeating: false, count: locations.count)
    var queue: [(x: Int, y: Int)] = [(sangenX, sangenY)]
    
    while !queue.isEmpty {
        let target = queue.removeFirst()
        
        if target == locations.last! {
            return true
        }
        
        for idx in 0..<locations.count {
            if abs(target.x - locations[idx].x)+abs(target.y - locations[idx].y) <= 1000 && !visited[idx] {
                visited[idx] = true
                queue.append((locations[idx].x, locations[idx].y))
            }
        }
    }
    return false
}
