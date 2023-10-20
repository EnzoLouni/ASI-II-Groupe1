export const selectRobots = (state) => state.robotsReducer.robots;
export const selectParts = (state) => state.partsReducer.parts;
export const selectSelectedParts = (state) =>
  state.partsReducer.parts.filter((part) =>
    state.partsReducer.selectedParts.includes(part.id)
  );