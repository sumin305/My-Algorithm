n = int(input())
junhyun_shares, seongmin_shares = 0, 0
junhyun_cashes, seongmin_cashes = n, n
up_count, down_count = 0, 0
stock = list(map(int, input().split()))

for i in range(len(stock)) :
    # 준현이
    if junhyun_cashes >= stock[i]:
        junhyun_shares += junhyun_cashes // stock[i]
        junhyun_cashes = junhyun_cashes % stock[i]
    
    if i == 0:
        continue
    if stock[i] > stock[i-1]:
        up_count += 1
        down_count = 0
    elif stock[i] < stock[i-1]:
        down_count += 1
        up_count = 0
    if up_count >= 3:
        seongmin_cashes += stock[i] * seongmin_shares
        seongmin_shares = 0
        up_count = 0
    elif down_count >= 3:
        if seongmin_cashes >= stock[i]:
            seongmin_shares += seongmin_cashes // stock[i]
            seongmin_cashes = seongmin_cashes % stock[i]

if junhyun_cashes + stock[-1] * junhyun_shares > seongmin_cashes + stock[-1] * seongmin_shares :
    print("BNP")
elif junhyun_cashes + stock[-1] * junhyun_shares < seongmin_cashes + stock[-1] * seongmin_shares :
    print("TIMING")
else :
    print("SAMESAME")