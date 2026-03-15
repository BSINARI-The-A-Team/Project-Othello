import java.util.*;

record MoveValue(int utilityValue, Position move) {}

public class MinimaxABP implements IOthelloAI{
    private int fixedDepth = 4;

	public Position decideMove(GameState state){
		return ALPHA_BETA_SEARCH(state);
	}

    public Position ALPHA_BETA_SEARCH(GameState state){
        System.out.println("Initiating Search");
        MoveValue moveValue = MAX_VALUE(state, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        Position position = moveValue.move();
        System.out.println("Final eval value: " + moveValue.utilityValue());
        return position;
    }

    public MoveValue MAX_VALUE(GameState state, int a, int b, int depth) {
        System.out.println("Max Value");
        /*
        if (IS_TERMINAL(state)) {
            return new MoveValue(UTILITY(state), null);
        } */

        if(IS_CUTOFF(state, depth)){
            System.out.println("Eval value: " + EVAL(state));
            return new MoveValue(EVAL(state), null);
        }

        ArrayList<Position> actions = ACTIONS(state);
        if (actions.isEmpty()) {
            // No moves available, pass the turn to opponent
            GameState passed = new GameState(state.getBoard(), state.getPlayerInTurn());
            passed.changePlayer();
            return MIN_VALUE(passed, a, b, depth+1);
        }
        int v = Integer.MIN_VALUE;
        Position move = null;
        for (Position position : ACTIONS(state)) {
            MoveValue minValue = MIN_VALUE(RESULT(state, position), a, b, depth+1);
            int v2 = minValue.utilityValue();
            if (v2 > v) {
                v = v2;
                move = position;
                a = Max(a, v);
            }
            if (v >= b) return new MoveValue(v,move);
        }
        return new MoveValue(v, move);
    }

    public MoveValue MIN_VALUE(GameState state, int a, int b, int depth) {
        System.out.println("Min Value");
        /*
        if (IS_TERMINAL(state)) {
            return new MoveValue(UTILITY(state), null);
        } */
        if(IS_CUTOFF(state, depth)){
            System.out.println("Eval value: " + EVAL(state));
            return new MoveValue(EVAL(state), null);
        }

        ArrayList<Position> actions = ACTIONS(state);
        if (actions.isEmpty()) {
            // No moves available, pass the turn to opponent
            GameState passed = new GameState(state.getBoard(), state.getPlayerInTurn());
            passed.changePlayer();
            return MAX_VALUE(passed, a, b, depth+1);
        }
        int v = Integer.MAX_VALUE;
        Position move = null;
        for (Position position : ACTIONS(state)) {
            MoveValue maxValue = MAX_VALUE(RESULT(state, position), a, b, depth+1);
            int v2 = maxValue.utilityValue();
            if (v2 < v) {
                v = v2;
                move = position;
                b = Min(b, v);
            }
            if (v <= a) return new MoveValue(v, move);
        }
        return new MoveValue(v, move);
    }

    public int UTILITY(GameState state){
        int[] playerTokens = state.countTokens();
        if(playerTokens[0] > playerTokens[1]){
            return 1;
        }
        else if(playerTokens[0] == playerTokens[1]){
            return 0;
        }
        else {
            return -1;
        }
    }

    public boolean IS_TERMINAL(GameState state){
        return state.isFinished();
    }

    public int EVAL(GameState state){
        int[] playerTokens = state.countTokens();

        if(state.getPlayerInTurn() == 1){
            return playerTokens[1] - playerTokens[0];
        }
        else {
            return playerTokens[0] - playerTokens[1];
        }
    }

    public boolean IS_CUTOFF(GameState state, int depth){
        if(depth > fixedDepth || IS_TERMINAL(state)) return true;
        else {return false;}
    }

    public ArrayList<Position> ACTIONS(GameState state){
        return state.legalMoves();  
    }

    public GameState RESULT(GameState state, Position position){
        GameState newState = new GameState(state.getBoard(), state.getPlayerInTurn());
        if(newState.insertToken(position)){
            return newState;
        } else {
            return null;
        }
    }

    public int Max(int x, int y){
        if (x > y) return x;
        else return y;
    }

    public int Min(int x, int y){
        if (x < y) return x;
        else return y;
    }
}

/*
function ALPHA-BETA-SEARCH(state, state) returns an action
    player←game.TO-MOVE(state)
    value, move←MAX-VALUE(state, state,−∞,+∞)
    return move

function MAX-VALUE(state, state,α, β) returns a (utility, move) pair
    if state.IS-TERMINAL(state) then return state.UTILITY(state, player), null
    v←−∞
    for each a in state.ACTIONS(state) do
        v2, a2←MIN-VALUE(state, state.RESULT(state, a),α, β)
        if v2 > v then
            v, move←v2, a
            α←MAX(α, v)
        if v ≥ β then return v, move
    return v, move

function MIN-VALUE(state, state,α, β) returns a (utility, move) pair
    if state.IS-TERMINAL(state) then return state.UTILITY(state, player), null
    v←+∞
    for each a in state.ACTIONS(state) do
        v2, a2←MAX-VALUE(state, state.RESULT(state, a),α, β)
        if v2 < v then
            v, move←v2, a
            β←MIN(β, v)
        if v ≤ α then return v, move
    return v, move
*/