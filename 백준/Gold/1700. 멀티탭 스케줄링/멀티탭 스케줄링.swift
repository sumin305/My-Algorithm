import Foundation

let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (N,K) = (n[0], n[1])
var plug: [Int] = []
let elec: [Int] = Array(readLine()!.split(separator: " ").map{Int(String($0))!})
var count = 0

for i in 0..<K{
    if plug.contains(elec[i]){
        continue
    }
    if plug.count != N{
        plug.append(elec[i])
        continue
    }
    var temp = 0
    var far = 0
    for p in plug{
        if !elec[i...].contains(p){
            temp = p
            break
        }
        let idx = elec[i...].firstIndex(of: p)!
        if idx > far{
            far = idx
            temp = p
        }
    }
    plug.remove(at: plug.firstIndex(of: temp)!)
    plug.append(elec[i])
    count += 1
}
print(count)