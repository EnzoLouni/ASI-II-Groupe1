export const selectCards = (state) => state.cardsReducer.cards;
// export const selectParts = (state) => state.partsReducer.parts;
export const selectSelectedCard = (state) =>
  state.cardsReducer.cards.filter((card) =>
    state.cardsReducer.cardsReducer.includes(card.id)
  );