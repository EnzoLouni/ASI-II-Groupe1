import { configureStore } from '@reduxjs/toolkit'
import zozzemonsReducer from './reducers/zozzemonsSlice'

export default configureStore({
  reducer: {
    zozzemon: zozzemonsReducer
  }
})