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
        
    public boolean isValid() {
        for(int rank = 0; rank < size; rank++) {
            for(int file = 0; file < size; file++) {
                if(!checkMinorDiagonal(rank, file) || !checkMajorDiagonal(rank, file) || !checkRankFile(rank, file)) {
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