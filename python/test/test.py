# def fibo(n):
#     return n
#

test = int(input())
arr = []
inp = input()
inp = inp.split(" ")

for i in range(test):
    arr.append(int(inp[i]))

dp = [[]]
for i in range(test):
    print()