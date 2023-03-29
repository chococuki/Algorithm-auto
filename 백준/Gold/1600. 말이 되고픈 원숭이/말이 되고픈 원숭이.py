from collections import deque
K = int(input())
W, H = map(int, input().split())
chess = []
chess_queue = deque()


def bfs():
    
    chess_queue.append((0,0,K,0))
    
    dx = [0, 0, -1, 1]
    dy = [-1, 1, 0, 0]
    hx = [-2, -1, 1, 2, 2, 1, -1, -2]
    hy = [1, 2, 2, 1, -1, -2, -2, -1]

    visited[0][0][K] = 1

    while chess_queue:
        y, x, k, cnt = chess_queue.popleft()

        
        if y == H-1 and x == W-1:
            return cnt
        
        if k > 0:
            for m in range(8):
                nx = x+hx[m]
                ny = y+hy[m]

                if 0 <= nx < W and 0 <= ny < H and chess[ny][nx] == 0:
                    if visited[ny][nx][k-1] == 0:
                        visited[ny][nx][k-1] = 1 
                        chess_queue.append((ny,nx,k-1,cnt+1))

        for i in range(4): 
            nx = x+dx[i]
            ny = y+dy[i]

            if 0 <= nx < W and 0 <= ny < H and chess[ny][nx] == 0:
                if visited[ny][nx][k] == 0:
                    visited[ny][nx][k] = 1
                    chess_queue.append((ny, nx, k,cnt+1))

               
    return -1


for i in range(H):
    chess.append(list(map(int, input().split())))

visited = [[[0]*(K+1) for _ in range(W)]for _ in range(H)]
print(bfs())