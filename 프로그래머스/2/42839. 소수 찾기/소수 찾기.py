import itertools

def solution(numbers):
    prime_set = set()
    def is_prime(n):
        if n < 2: return False
        a = 2
        while a < n:
            if n % a == 0:
                return False
            a += 1
        return True
    
    for i in range(1, len(numbers)+1):
        str_list = list(itertools.permutations(numbers, i))
        for str in str_list:
            temp = "".join(str)
            prime_set.add(int(temp))
    prime_set = list(filter(is_prime, prime_set))
    return len(prime_set)