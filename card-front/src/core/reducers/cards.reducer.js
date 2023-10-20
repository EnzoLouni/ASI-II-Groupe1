const initialState = {
    parts: [],
    selectedParts: [],
  };
  
  export const cardsReducer = (state = initialState, action) => {
    switch (action.type) {
      case 'LOAD_CARDS':
        return {
          ...state,
          cards: action.payload,
        };
      case 'SELECT_CARD':
        return {
          ...state,
          selectedCard: action.payload,
        };
      default:
        return state;
    }
  };