T = int(input())

for _ in range(T):
    N = int(input())
    dic = {}

    for _ in range(N):
        a, b = input().split()
        if b in dic:
            dic[b] += 1
        else:
            dic[b] = 1
    result = 1
    for d in dic.values():
        result *= (d+1)
    print(result-1)