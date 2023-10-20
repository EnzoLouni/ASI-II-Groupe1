const initialState = {
    parts: [],
    selectedParts: [],
  };
  
  export const cardsReducer = (state = initialState, action) => {
    switch (action.type) {
      case 'LOAD_PARTS':
        return {
          ...state,
          parts: action.payload,
        };
      case 'SELECT_PARTS':
        return {
          ...state,
          selectedParts: action.payload,
        };
      default:
        return state;
    }
  };