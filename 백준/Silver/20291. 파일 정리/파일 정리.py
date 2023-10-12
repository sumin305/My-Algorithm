N = int(input())
dic = {}
for _ in range(N):
    str, ex = input().split(".")
    if ex in dic: dic[ex] += 1
    else: dic[ex] = 1
for i in sorted(dic.items()):
    print(i[0], i[1])