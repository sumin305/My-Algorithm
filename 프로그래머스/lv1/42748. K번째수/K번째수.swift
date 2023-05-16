import Foundation

func solution(_ array:[Int], _ commands:[[Int]]) -> [Int] {
    var result: [Int] = []
    for command in commands {
        let arr = Array(array[(command[0]-1)...(command[1]-1)]).sorted()
        result.append(arr[command[2]-1])
    }
    return result
}