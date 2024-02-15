import Foundation

typealias Tomato = (z: Int, x: Int, y: Int)

func solution() -> Int {
	// 6가지의 방향
    let d = [(-1,0,0), (1,0,0), (0,-1,0), (0,1,0), (0,0,-1), (0,0,1)]
    
    let mnh = readLine()!.split(separator: " ").map { Int(String($0))! }
    let m = mnh[0]
    let n = mnh[1]
    let h = mnh[2]
    var boxes = [[[Int]]](repeating: [[Int]](repeating: [Int](), count: n), count: h)
    var result = 0
    // 토마토 입력 받기
    for z in 0..<h {
        for x in 0..<n {
            let rows = readLine()!.split(separator: " ").map { Int(String($0))! }
            boxes[z][x].append(contentsOf: rows)
        }
    }
    
    var q = [Tomato]()
    
    func bfs() {
        var index = 0

        while index < q.count {
            let now = q[index]
            index += 1
            // 6가지의 방향으로 탐색하기 위해서 6번의 반복문을 돌린다. 
            for i in 0..<6 {
                let nx = now.x+d[i].0
                let ny = now.y+d[i].1
                let nz = now.z+d[i].2
                // 박스 내에서 
                if 0<=nx, nx<n, 0<=ny, ny<m, 0<=nz, nz<h {
                	// 탐색했을 때 0이라면
                    if boxes[nz][nx][ny] == 0 {
                        q.append((nz,nx,ny))
                        // 토마토가 익는 일 수를 갱신해준다. 
                        boxes[nz][nx][ny] = boxes[now.z][now.x][now.y] + 1
                    }
                }
            }
        }
    }
    
    // 탐색 전 익은 토마토의 경우 큐에 넣어준다. 
    for z in 0..<h {
        for x in 0..<n {
            for y in 0..<m {
                if boxes[z][x][y] == 1 {
                    q.append((z,x,y))
                }
            }
        }
    }
    
    bfs()
    
    // 탐색이 끝난 후
    for z in 0..<h {
        for x in 0..<n {
            for y in 0..<m {
            	// 0이 존재한다면 -1 리턴
                if boxes[z][x][y] == 0 {
                    return -1
                }
                // 제일 큰 값이 최소 일수가 된다.
                result = max(result, boxes[z][x][y])
            }
        }
    }
    // 시작했을 때 1을 빼준다. 
    return result == 1 ? 0 : result-1
}

print(solution())