while(True):
    line = input()
    if (line == 'end'):
        break

    board = [[''] * 3 for _ in range(3)]
    
    xCount = 0
    oCount = 0
    dotCount = 0
    for i, c in enumerate(line.rstrip()):
        board[i // 3][i % 3] = c
        if c == 'X':
            xCount += 1
        elif c == 'O':
            oCount += 1
        else:
            dotCount += 1

    if (abs(xCount - oCount) > 1 or oCount > xCount):
        print('invalid')
        continue

    def isValid():
        xBingoCount = 0
        oBingoCount = 0
        pos = set()
        # 가로
        for i in range(3):
            if (board[i][0] == board[i][1] == board[i][2]):
                if (board[i][0] == 'X'):
                    pos.add((i, 0))
                    pos.add((i, 1))
                    pos.add((i, 2))
                    xBingoCount += 1
                elif (board[i][0] == 'O'):
                    oBingoCount += 1
                    pos.add((i, 0))
                    pos.add((i, 1))
                    pos.add((i, 2))
        
        # 세로
        for j in range(3):
            if (board[0][j] == board[1][j] == board[2][j]):
                if (board[0][j] == 'X'):
                    xBingoCount += 1
                    pos.add((0, j))
                    pos.add((1, j))
                    pos.add((2, j))
                elif (board[0][j] == 'O'):
                    oBingoCount += 1
                    pos.add((0, j))
                    pos.add((1, j))
                    pos.add((2, j))

        # 대각선
        if (board[0][0] == board[1][1] == board[2][2]):
            if (board[0][0] == 'X'):
                xBingoCount += 1
                pos.add((0, 0))
                pos.add((1, 1))
                pos.add((2, 2))
            elif (board[0][0] == 'O'):
                oBingoCount += 1
                pos.add((0, 0))
                pos.add((1, 1))
                pos.add((2, 2))
        if (board[0][2] == board[1][1] == board[2][0]):
            if (board[0][2] == 'X'):
                xBingoCount += 1
                pos.add((0, 0))
                pos.add((1, 1))
                pos.add((2, 2))
            elif (board[0][2] == 'O'):
                oBingoCount += 1
                pos.add((0, 0))
                pos.add((1, 1))
                pos.add((2, 2))
        
        if (xBingoCount == 1 and oBingoCount == 0 and xCount == oCount + 1):
            return True
        elif (oBingoCount == 1 and xBingoCount == 0 and oCount == xCount):
            return True
        elif (xBingoCount == 2 and oBingoCount == 0 and len(pos) % 2 == 1 and xCount == oCount + 1):
            return True
        elif (oBingoCount == 2 and xBingoCount == 0 and len(pos) % 2 == 1 and oCount == xCount):
            return True
        elif (dotCount == 0 and xBingoCount == 0 and oBingoCount == 0):
            return True
        return False

    print('valid' if isValid() else 'invalid')


