N = int(input())
target = int(input())
result = ""
array = [[0 for i in range(N)] for i in range(N)]
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

count = 1
i = 1

x = int((N-1)/2)
y = int((N-1)/2)

array[x][y] = i
if i == target: result = (x+1, y+1)
for count in range(1, int((N-1)/2) + 1):
    x += dx[-1]
    y += dy[-1]
    i += 1
    array[x][y] = i
    if i == target: result = (x+1, y+1)

    for j in range(4):
        if j == 0: 
            for k in range(2*count - 1):
                x += dx[j]
                y += dy[j]
                i += 1
                array[x][y] = i
                if i == target: result = (x+1, y+1)

        else:
            for k in range(2*count):
                x += dx[j]
                y += dy[j]
                i += 1
                if i == N*N + 1: break
                array[x][y] = i 
                if i == target: result = (x+1, y+1)

for a in array :
    print(*a)
print(*result)