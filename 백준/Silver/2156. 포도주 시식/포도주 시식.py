n = int(input())
array = [0]*(n+1)
dp = [0]*(n+1)


for i in range(1, n+1):
    array[i] = int(input())

if n == 1:
    print(array[1])
elif n == 2:
    print(array[1]+array[2])
else:
    dp[1] = array[1]
    dp[2] = array[1] + array[2]
    for i in range(3, n+1):
        dp[i] = max(dp[i-1], dp[i-3]+array[i-1]+array[i], dp[i-2]+array[i])
    print(dp[n])