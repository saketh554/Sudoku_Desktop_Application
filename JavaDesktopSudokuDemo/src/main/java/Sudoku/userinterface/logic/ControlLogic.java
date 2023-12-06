package Sudoku.userinterface.logic;

import Sudoku.computationlogic.GameLogic;
import Sudoku.constants.GameState;
import Sudoku.constants.Messages;
import Sudoku.problemdomain.IStorage;
import Sudoku.problemdomain.SudokuGame;
import Sudoku.userinterface.IUserInterfaceContract;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;
    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuinput(int x, int y, int input) {
        try{
            SudokuGame gameData= storage.getGameData();
            int[][] newGrodState= gameData.getCopyOfGridState();
            newGrodState[x][y] = input;
            gameData=new SudokuGame(
                    GameLogic.checkForCompletion(newGrodState),
                    newGrodState
            );
            storage.updateGameData(gameData);
            view.updateSquare(x,y,input);
            if (gameData.getGameState()== GameState.COMPLETE){
                view.showDialog(Messages.GAME_COMPLETE);
            }
        }catch (IOException e){
            e.printStackTrace();
            view.showError(Messages.ERROR);
        }

    }

    @Override
    public void onDialogClick() {
        try {
            storage.updateGameData(
                    GameLogic.getNewGame()
            );
            view.updateBoard(storage.getGameData());

        }catch (IOException e){
            view.showError(Messages.ERROR);

        }

    }
}
