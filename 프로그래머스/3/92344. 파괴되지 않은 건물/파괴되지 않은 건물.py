def solution(board, skill):
    answer = 0
    sum = [[0 for i in range(len(board[0])+1)] for i in range(len(board)+1)]
    prefix_sum = [[1 for i in range(len(board[0])+1)] for i in range(len(board)+1)]
    a, b = 0, 0
    
    for type, r1, c1, r2, c2, degree in skill:
        if type == 1: #공격일 경우
            a, b = -1, 1
        else:
            a, b = 1, -1
        sum[r1][c1] += a*degree
        sum[r1][c2+1] += b*degree
        sum[r2+1][c1] += b*degree
        sum[r2+1][c2+1] += a*degree

    prefix_sum[0][0] = sum[0][0]
    # 구간합 구하기
    for x in range(len(board)+1):
        for y in range(len(board[0])+1):
            if x == 0 and y == 0: 
                continue
            if x == 0: 
                prefix_sum[x][y] = prefix_sum[x][y-1] + sum[x][y]
            elif y == 0: 
                prefix_sum[x][y] = prefix_sum[x-1][y] + sum[x][y]
            else:
                prefix_sum[x][y] = prefix_sum[x][y-1] + prefix_sum[x-1][y] - prefix_sum[x-1][y-1] + sum[x][y]


    # 구간합과 board 더하기
    for x in range(len(board)):
        for y in range(len(board[0])): 
            sum[x][y] = prefix_sum[x][y] + board[x][y]
            if sum[x][y] > 0: 
                answer += 1
    
    
    return answer