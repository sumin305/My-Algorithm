import itertools

L, C = map(int, input().split())

vowels = set(['a', 'e', 'i', 'o', 'u'])
alphas = set(list(input().split()))
result = []
candidates = []
# 자음
consonants = list(alphas - vowels)
# 모음 
vowels = list(vowels & alphas)

for i in range(L-2):
    candidates.append((2+i, 1+(L-3-i)))

for candidate in candidates:
    consonants_arr = list(map(''.join,itertools.combinations(consonants, candidate[0])))
    vowels_arr = list(map(''.join,itertools.combinations(vowels, candidate[1])))
    for consonant in consonants_arr:
        for vowel in vowels_arr:
            temp = consonant + vowel
            temp = "".join(sorted(temp))
            result.append(temp)
result.sort()

for i in result:
    print(i)