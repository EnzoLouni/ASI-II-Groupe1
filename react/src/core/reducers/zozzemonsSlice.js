import { createSlice } from '@reduxjs/toolkit'

export const zozzemonSlice = createSlice({
  name: 'zozzemon',
  initialState: {
    zozzemonList: [],
    selectedZozzemon: null
  },
  reducers: {
    setZozzemonList: (state, action) => {
      state.zozzemonList = action.payload
    },
    setSelectedZozzemon: (state, action) => {
      state.selectedZozzemon = state.zozzemonList.find(z => z.id === action.payload)
    },
  }
})

// Action creators are generated for each case reducer function
export const { setZozzemonList, setSelectedZozzemon } = zozzemonSlice.actions

export default zozzemonSlice.reducer
