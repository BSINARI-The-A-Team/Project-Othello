import java.util.*;
 
public class MinimaxABP implements IOthelloAI{

	public Position decideMove(GameState s){
		/**
         * Add MinimaxABP functions here
         * when they are done
         */
        return new Position(-1,-1); // placeholder return value
	}

    public void ALPHA_BETA_SEARCH(GameState s){}

    public void MAX_VALUE(GameState s, int alpha, int beta){}

    public void MIN_VALUE(GameState s, int alpha, int beta){}

    public void UTILITY(GameState s, int currentPlayer){}

    public void IS_TERMINAL(GameState s){}

    public void ACTIONS(GameState s){}
}

/*
function ALPHA-BETA-SEARCH(game, state) returns an action
    player←game.TO-MOVE(state)
    value, move←MAX-VALUE(game, state,−∞,+∞)
    return move

function MAX-VALUE(game, state,α, β) returns a (utility, move) pair
    if game.IS-TERMINAL(state) then return game.UTILITY(state, player), null
    v←−∞
    for each a in game.ACTIONS(state) do
        v2, a2←MIN-VALUE(game, game.RESULT(state, a),α, β)
        if v2 > v then
            v, move←v2, a
            α←MAX(α, v)
        if v ≥ β then return v, move
    return v, move

function MIN-VALUE(game, state,α, β) returns a (utility, move) pair
    if game.IS-TERMINAL(state) then return game.UTILITY(state, player), null
    v←+∞
    for each a in game.ACTIONS(state) do
        v2, a2←MAX-VALUE(game, game.RESULT(state, a),α, β)
        if v2 < v then
            v, move←v2, a
            β←MIN(β, v)
        if v ≤ α then return v, move
    return v, move
*/