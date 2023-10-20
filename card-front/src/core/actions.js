export const loadRobots = (robots) => ({
    type: 'LOAD_ROBOTS',
    payload: robots,
  });
  
  export const loadParts = (parts) => ({
    type: 'LOAD_PARTS',
    payload: parts,
  });
  
  export const selectParts = (parts) => ({
    type: 'SELECT_PARTS',
    payload: parts,
  });