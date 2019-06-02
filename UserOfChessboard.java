import java.util.*;
public class UserOfChessboard {
    
    public static void main(String[] args) {
        Chessboard testBoard = new Chessboard(8); //constructor parameter will determine n-size problem when run, this starts to chug at n=10.
        ArrayList<Chessboard> testSet = new ArrayList<Chessboard>();
        testSet.add(testBoard);
        System.out.println(nQueens(0, testSet).size());
        for(int i = 0; i < nQueens(0, testSet).size(); i++)
            System.out.println(nQueens(0, testSet).get(i));
    }
    
    public static ArrayList<Chessboard> nQueens(int currentRank, ArrayList<Chessboard> solutionSet)    {
        int ranks = solutionSet.get(0).size;
        ArrayList<Chessboard> tempSolutions = new ArrayList<Chessboard>();
        if(currentRank != ranks) {
            for (Chessboard solution : solutionSet) {      
                for(int file = 0; file < ranks; file++) {
                    Chessboard newSolution = new Chessboard(ranks);
                    assignment(solution, newSolution);
                    newSolution.piecePlacer(currentRank, file, 1);
                    if(newSolution.isValid()) tempSolutions.add(newSolution);              
                }
            }
            return nQueens(1 + currentRank, tempSolutions);         
        }
        else {
            return solutionSet;
        }
    }
    
    public static void assignment(Chessboard input, Chessboard output) {
        for(int rank = 0; rank < input.size; rank++)
            for(int file = 0; file < input.size; file++)
                output.board[rank][file] = input.board[rank][file];
    }
    
}