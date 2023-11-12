T = int(input())

for case in range(T):
    N = int(input())
    arr = [[0 for i in range(N)] for j in range(N)]
    
    arr[0] = [i+1 for i in range(N)]
    vector = 1
    number = N+1

    temp = N-1 
    round = N-1 
    count = 2 
    loc_x, loc_y = (1, N-1)
    
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    
    while number <= N*N:
        arr[loc_x][loc_y] = number
        temp -= 1
        if temp == 0:
            count -= 1

            if count == 0:
                round -= 1
                
            vector += 1

            if vector == 4:
                vector = 0

            temp = round
        
        # 위치 변경
        loc_x += dx[vector]
        loc_y += dy[vector]
        number += 1

    print(f"#{case+1}")
    for a in arr:
        print(*a)
