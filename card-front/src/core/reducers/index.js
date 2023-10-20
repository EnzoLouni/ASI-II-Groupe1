import { combineReducers } from 'redux';
import { cardsReducer } from "./cards.reducer"

const globalReducer = combineReducers({
    cardsReducer: cardsReducer,
});

export default globalReducer;