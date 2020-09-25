# 9935 문자열 폭발

# 테스트케이스
# a = "12ab112ab2ab"
# b = "12ab"
#
# a = "aaaa"
# b = "a"
#
# a = "mirkovC4nizCC44"
# b = "C4"
#
# a = "12ab12ab12abb"
# b = "12ab"

a = str(input())
b = str(input())

len_a = len(a)
n = len(b)
# normal = [i for i in range(len(a))]
nexts = [i for i in range(len(a))]
before = [i-1 for i in range(len(a))]
# normal = [-1] + normal
nexts = nexts + [-1]
before = [-1] + before
i = 0

while True:
    # print("i", i)
    # print(before)
    # print(normal)
    # print(nexts)

    if i >= len_a:
        break

    # 마지막 글자 비교
    if a[i] == b[-1]:
        end = i
        cur = i
        flag = True

        for j in range(n-1, -1, -1):
            # print("j: ", j, "cur : ", cur, ", ",a[cur], b[n-1-j])
            if cur < 0 or j < 0:
                flag = False
                break
            if a[cur] != b[j]:
                flag = False
                break
            cur = before[cur+1]  # 링크따라 뒤로 탐색

        if flag:
            # 삭제
            # print("cur: ", cur, "end : ", end)

            # 인덱스 위치 맞추기
            tmp = nexts[end+1]
            # print(tmp)
            nexts[cur+1] = tmp # 0이면?
            before[tmp+1] = cur  # 0이면?

    i += 1
    # print()


i = nexts[0]
ans = ''

while True:
    if i == -1 or i >= len_a:
        break
    # print(a[i], end='')
    ans += a[i]
    i = nexts[i+1]

if ans == '':
    ans = 'FRULA'

print(ans)