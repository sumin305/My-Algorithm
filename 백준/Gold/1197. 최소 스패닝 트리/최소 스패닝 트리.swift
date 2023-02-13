import Foundation

func findSet(_ target: Int)-> Int{
    if parent[target] == target{
        return target
    }else{
        parent[target] = findSet(parent[target])
        return parent[target]
    }
}
func unionSet(_ a: Int, _ b :Int){
    if a < b{
        parent[findSet(b)] = a
    }else{
        parent[findSet(a)] = b
    }
}

let n = readLine()!.split(separator: " ").map{Int(String($0))!}
let (V,E) = (n[0], n[1])
var weight: Int = 0
var edge: [[Int]] = Array()
var parent: [Int] = Array(0...V)
for i in 0..<E{
    edge.append(readLine()!.split(separator: " ").map{Int(String($0))!})
}

for e in edge.sorted(by: {$0[2] < $1[2]}){
    let (u,v) = (e[0], e[1])
    if findSet(u) != findSet(v){
        unionSet(u,v)
        weight += e[2]
    }
}
print(weight)