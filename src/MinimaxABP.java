import java.util.*;

record MoveValue(int utilityValue, Position move) {}

public class MinimaxABP implements IOthelloAI{

	public Position decideMove(GameState s){
		/**
         * Add MinimaxABP functions here
         * when they are done
         */
        return new Position(-1,-1); // placeholder return value
	}

    public Position ALPHA_BETA_SEARCH(GameState state){
        /*
        player←game.TO-MOVE(state)
        value, move←MAX-VALUE(state, state,−∞,+∞)
        */
        return new Position(-1,-1); // placeholder return value
    }

    public MoveValue MAX_VALUE(GameState state) {
    if (IS_TERMINAL(state)) {
        return new MoveValue(UTILITY(state), null);
    }
    int v = Integer.MIN_VALUE;
    Position move = null;
    for (Position position : ACTIONS(state)) {
        MoveValue minValue = MIN_VALUE(RESULT(state, position));
        int v2 = minValue.utilityValue();
        if (v2 > v) {
            v = v2;
            move = position;
            }
        }
        return new MoveValue(v, move);
    }

    public MoveValue MIN_VALUE(GameState state) {
        if (IS_TERMINAL(state)) {
            return new MoveValue(UTILITY(state), null);
        }
        int v = Integer.MAX_VALUE;
        Position move = null;
        for (Position position : ACTIONS(state)) {
            MoveValue maxValue = MAX_VALUE(RESULT(state, position));
            int v2 = maxValue.utilityValue();
            if (v2 < v) {
                v = v2;
                move = position;
            }
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

    public ArrayList<Position> ACTIONS(GameState state){
        return state.legalMoves();  
    }

    public GameState RESULT(GameState state, Position position){
        GameState newState = state;
        if(newState.insertToken(position)){
            return newState;
        } else {
            return null;
        }
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