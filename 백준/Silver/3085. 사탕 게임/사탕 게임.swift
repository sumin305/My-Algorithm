import Foundation

//동서남북으로 확인해서 자신과 다른 블록이면
//자리를 바꿔본다
//바꾼 결과 먹을 수 있는 사탕의 개수는
// 1. 같은 열의 사탕과 바꾸었을 경우 원래 행, 바꾼 행, 원래 열 확인
// 2. 같은 행의 사탕과 바꾸었을 경우 원래 열, 바꾼 열, 원래 행 확인
//연속 사탕 카운트 방법은? 해당 배열 돌면서 이전 항목과 같은 것일때 count 다른 것 나오면 min값 초기화

let N = Int(readLine()!)!
var board: [[Character]] = Array(repeating: Array(repeating: "." , count: N+2), count: N+2)
for i in 1...N{
    board[i][1...N] = ArraySlice(readLine()!)
}
var candy_max = 0

func changeNum(_ a: (Int, Int), _ b : (Int, Int))  -> (){
    let temp = board[a.0][a.1]
    board[a.0][a.1] = board[b.0][b.1]
    board[b.0][b.1] = temp
}

func rowSeqMax(row: Int) -> Int{
    var temp_count = 1
    var temp_max = 0
    for k in 2...N{
        if board[row][k] != board[row][k-1]{
            temp_count = 1
        }else{
            temp_count += 1
        }
        temp_max = max(temp_max, temp_count)
    }
    return temp_max
}

func colSeqMax(col: Int) -> Int{
    var temp_count = 1
    var temp_max = 0
    for k in 2...N{
            if board[k][col] != board[k-1][col]{
                temp_count = 1
            }else{
                temp_count += 1
            }
            temp_max = max(temp_max, temp_count)
        }
    
    return temp_max
}

for i in 1...N{
    candy_max = max(candy_max,rowSeqMax(row: i))
}

for i in 1...N{
    candy_max = max(candy_max,colSeqMax(col: i))
}
for i in 1...N{
      for j in 1...N{
        //동쪽 확인
        if board[i][j+1] != board[i][j] &&  board[i][j+1] != "."{
            changeNum((i,j), (i,j+1))
            candy_max = max(candy_max,rowSeqMax(row: i))
            candy_max = max(candy_max,colSeqMax(col: j))
            candy_max = max(candy_max,colSeqMax(col: j+1))
            changeNum((i,j), (i,j+1))
        }
        
        //서쪽 확인
        if board[i][j-1] != board[i][j] &&  board[i][j-1] != "."{
            changeNum((i,j), (i,j-1))
            candy_max = max(candy_max,rowSeqMax(row: i))
            candy_max = max(candy_max,colSeqMax(col: j))
            candy_max = max(candy_max,colSeqMax(col: j-1))
            changeNum((i,j), (i,j-1))
        }
        
        //북쪽 확인
        if board[i-1][j] != board[i][j] &&  board[i-1][j] != "."{
            changeNum((i,j), (i-1,j))
            candy_max = max(candy_max,colSeqMax(col: j))
            candy_max = max(candy_max,rowSeqMax(row: i))
            candy_max = max(candy_max,rowSeqMax(row: i-1))
            changeNum((i,j), (i-1,j))
        }
        
        //남쪽 확인
        if board[i+1][j] != board[i][j] &&  board[i+1][j] != "."{
            changeNum((i,j), (i+1,j))
            candy_max = max(candy_max,colSeqMax(col: j))
            candy_max = max(candy_max,rowSeqMax(row: i))
            candy_max = max(candy_max,rowSeqMax(row: i+1))
            changeNum((i,j), (i+1,j))
        }
    }
}
print(candy_max)
