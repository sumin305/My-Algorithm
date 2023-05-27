
let input = readLine()!.split(separator: " ").map { Int($0)! }
let a = input[0], b = input[1], c = input[2]

func dfs(a: Int, b: Int, c: Int) -> Int {
    if b == 1 {
        return a
    }
    
    if b % 2 != 0 {
        return a * dfs(a: a, b: b - 1, c: c)
    }
    
    let half = dfs(a: a, b: b / 2, c: c) % c
    return half * half % c
}

print(dfs(a: a, b: b, c: c) % c)