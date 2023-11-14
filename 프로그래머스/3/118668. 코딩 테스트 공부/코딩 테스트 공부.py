def solution(alp, cop, problems):
    max_alp, max_cop = 0, 0
    for problem in problems:
        if problem[0] > max_alp:
            max_alp = problem[0]
        if problem[1] > max_cop:
            max_cop = problem[1]    
    alp = min(alp, max_alp)
    cop = min(cop, max_cop)

    skills = [[int(1e9) for i in range(max_alp+1)] for j in range(max_cop+1)]
    
    skills[cop][alp] = 0
        
    # dp 값 초기화
    for x in range(cop, max_cop+1):
        for y in range(alp, max_alp+1):       
            if x + 1 <= max_cop:
                skills[x + 1][y] = min(skills[x][y]+1, skills[x + 1][y])
            if y + 1 <= max_alp:
                skills[x][y + 1] = min(skills[x][y]+1, skills[x][y + 1])

            # 문제들 중, 현재 능력으로 풀 수 있는 것들은 dp 채워주기
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if y >= alp_req and x >= cop_req: # 풀 수 있음
                    next_alp, next_cop = min(max_alp, y + alp_rwd), min(max_cop, x + cop_rwd)               
                    skills[next_cop][next_alp] = min(skills[next_cop][next_alp], skills[x][y] + cost)

    return skills[-1][-1]