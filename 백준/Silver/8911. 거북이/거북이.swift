import Foundation

let T = Int(readLine()!)!

for _ in 0..<T {
    let S = readLine()!
    var (minX, minY) = (0, 0)
    var (maxX, maxY) = (0, 0)
    let dx = [-1,0,1,0]
    let dy = [0,1,0,-1]
    var turtle = (0, 0)
    var vector = 1
    for s in S {
        switch s {
            case "F":
                let nx = turtle.0 + dx[vector]
                let ny = turtle.1 + dy[vector]
                turtle = (nx, ny)
            case "B":
                let nx = turtle.0 + dx[(vector + 2) % 4]
                let ny = turtle.1 + dy[(vector + 2) % 4]
                turtle = (nx, ny)
            case "L":
                if vector == 0 {
                    vector = 3
                } else {
                    vector -= 1
                }
            default:
                vector = (vector + 1) % 4
        }
        if turtle.0 < minX { minX = turtle.0 }
        if turtle.0 > maxX { maxX = turtle.0 }
        if turtle.1 < minY { minY = turtle.1 }
        if turtle.1 > maxY { maxY = turtle.1 }
    }
    let resultX = maxX - minX
    let resultY = maxY - minY
    if resultX == 0 || resultY == 0 {
        print(0)
    } else {
        print(abs(resultX * resultY))
    }
}