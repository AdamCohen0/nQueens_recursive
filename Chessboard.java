public class Chessboard {
    
    int size;
    int[][] board;
    
    public Chessboard(int size) {
        this.size = size;
        this.board = new int[size][size];
        for(int rank = 0; rank < size; rank++)
            for(int file = 0; file < size; file++)
                piecePlacer(rank, file, 0);
    }
        
    public void piecePlacer(int rank, int file, int value) {
        board[rank][file] = value;
    }
    
    public boolean rankUnfilled(int rank) {
        int pieceCount = 0;
        for(int piece : board[rank]) {
            pieceCount += piece;
        }
        if(pieceCount > 0)
            return false;
            return true;
    }
    
    public boolean checkMajorDiagonal(int rank, int file) {
        int diagRank = rank;
        int diagFile = file;
        int trueCount = 0;
        while(diagRank > 0 && diagFile > 0) {
            diagRank--;
            diagFile--;
        }
        for(int i = 0; i <= size - 1 - Math.abs(diagRank - diagFile); i++)
            if(board[diagRank + i][diagFile + i] == 1) trueCount++;
        if(trueCount > 1)
            return false;
            return true;
    }
    
    public boolean checkMinorDiagonal(int rank, int file) {
        int diagRank = rank;
        int diagFile = file;
        int trueCount = 0;
        int iterator;
        while(diagRank < size - 1 && diagFile > 0) {
            diagRank++;
            diagFile--;
        }
        if(diagRank == size - 1) iterator = size - diagFile;
        else iterator = diagRank + 1;
        for(int i = 0; i < iterator; i++)
            if(board[diagRank - i][diagFile + i] == 1) trueCount++;
        if(trueCount > 1)
            return false;
            return true;
    }
    
    public boolean checkRankFile(int rank, int file) {
        int trueCount = 0;
        for(int i = 0; i < size; i++) {
            if(board[rank][i] == 1)
                trueCount++;
            if(board[i][file] == 1)
                trueCount++;
        }
        if(trueCount > 2)
            return false;
            return true;
    }
    
    public boolean checkRadius(int rank, int file) {
        boolean topEdge = false, bottomEdge = false, rightEdge = false, leftEdge = false;
        int pieceCount = 0;
        if(rank == 0) topEdge = true;
        if(rank == size - 1) bottomEdge = true;
        if(file == 0) leftEdge = true;
        if(file == size - 1) rightEdge = true;
        
        if(topEdge && leftEdge)
            pieceCount = board[0][1] + board[1][0] + board[1][1];
        if(topEdge && rightEdge)
            pieceCount = board[0][size - 2] + board[1][size - 2] + board[1][size - 1];
        if(bottomEdge && leftEdge)
            pieceCount = board[size - 2][0] + board[size - 2][1] + board[size - 1][1];
        if(bottomEdge && rightEdge)
            pieceCount = board[size - 2][size - 2] + board[size - 2][size - 1] + board[size - 1][size - 2];
        
        if((bottomEdge && !topEdge) && (bottomEdge && !rightEdge) && (bottomEdge &&!leftEdge)) {
            pieceCount = board[rank - 1][file];
            for(int i = -1; i <= 1; i+=2) 
                pieceCount += board[rank][file + i] + board[rank - 1][file + i];
        }
            
        if((topEdge && !bottomEdge) && (topEdge && !rightEdge) && (topEdge &&!leftEdge)) {
            pieceCount = board[rank + 1][file];
            for(int i = -1; i <= 1; i+=2)
                pieceCount += board[rank][file + i] + board[rank + 1][file + i];
        }

        if((rightEdge && !topEdge) && (rightEdge && !bottomEdge) && (rightEdge &&!leftEdge)) {
            pieceCount = board[rank][file - 1];
            for(int i = -1; i <= 1; i+=2)
                pieceCount += board[rank + i][file] + board[rank + i][file - 1];
        }
            
        if((leftEdge && !topEdge) && (leftEdge && !rightEdge) && (leftEdge &&!bottomEdge)) {
            pieceCount = board[rank][file + 1];
            for(int i = -1; i <= 1; i+=2)
                pieceCount += board[rank + i][file] + board[rank + i][file + 1];
        }
            
        if(!(topEdge || leftEdge || rightEdge || bottomEdge)) {
            for(int i = -1; i <= 1; i+=2)
                pieceCount += board[rank + i][file + i] + board[rank - i][file + i] + board[rank + i][file] + board[rank][file + i];
        }
            
        if(pieceCount > 0)
            return false;
            return true;
    }
        
    public boolean isValid() {
        for(int rank = 0; rank < size; rank++) {
            for(int file = 0; file < size; file++) {
                if(!checkMinorDiagonal(rank, file) || !checkMajorDiagonal(rank, file) || !checkRankFile(rank, file)) {
                    return false;
                }
                if (board[rank][file] == 1 && !checkRadius(rank, file)) {
                    return false;
            }
        }
        }
        return true;
    }
    
    public String toString() {
        String returnString = "";
        for(int rank = 0; rank < size; rank++) {
            for(int file = 0; file < size; file++) {
                returnString += board[rank][file] + " ";
            }
            returnString += System.lineSeparator();
        }
        returnString += System.lineSeparator();
        return returnString;
    }
    
}
          