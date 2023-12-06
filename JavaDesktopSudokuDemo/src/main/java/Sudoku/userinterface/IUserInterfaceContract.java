package Sudoku.userinterface;

import Sudoku.problemdomain.SudokuGame;

public interface IUserInterfaceContract {
    interface  EventListener{
        void  onSudokuinput(int x, int y, int input);
        void  onDialogClick();

    }
    interface  View{
        void setListener(IUserInterfaceContract.EventListener Listener);
        void  updateSquare(int x, int y, int input);
        void updateBoard(SudokuGame game);
        void showDialog(String Message);
        void  showError(String Message);
    }
}
