# 2615 오목
# 무승부 -> 0
# 어느 방향이 우선시?
# numpy 사용 x

def search(i, j, d, n, cur_len, dir_arr, board):
    # 가장 왼쪽 위 알에서 오른쪽/대각선으로 탐색
    # cur_len이 5면 끝
    # for i in range(n):
    #     for j in range(n):
    flag = False
    visit[i][j][d] = 1
    if cur_len > 5:
        return False

    ni = i + dir_arr[d][0]
    nj = j + dir_arr[d][1]

    if ni < n and ni >= 0 and nj < n and nj >= 0:
        if board[i][j] == board[ni][nj]:
            if visit[ni][nj][d] == 0:
                flag = search(ni, nj, d, n, cur_len+1, dir_arr, board)
            # flag를 받아줘야 마지막에 전달 가능!
        else:
            # print("!!!")
            flag = True if cur_len == 5 else False
    else:
        # print("!")
        flag = True if cur_len == 5 else False

    return flag


inp = ""
n = 19
board = []
visit = []
for i in range(n):
    tmp = []
    tmp2 = []
    for j in range(n):
        tmp.append(0)
        tmp3 = []
        for d in range(4):
            tmp3.append(0)
        tmp2.append(tmp3)
    board.append(tmp)
    visit.append(tmp2)

dir_arr = [[0, 1], [1, 0], [1, 1], [-1, 1]]  # 가장 위 -> 가장 왼쪽
flag = False
answer = -1
ansi = 0
ansj = 0

for i in range(n):
    inp = input()
    split_inp = inp.split(" ")
    for j in range(n):
        board[i][j] = int(split_inp[j])

for j in range(n):
    if flag:
        break

    for i in range(n):
        if flag:
            break
        if board[i][j] == 0:
            continue

        for d in range(4):
            if visit[i][j][d] != 0:
                continue
            flag = search(i, j, d, 19, 1, dir_arr, board)
            if flag:
                ansi = i+1
                ansj = j+1
                answer = board[i][j]
                print(board[i][j])
                break


if answer == -1:
    print(0)
else:
    print(int(answer))
    print(ansi, ansj)