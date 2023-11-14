import { configureStore } from '@reduxjs/toolkit'
import zozzemonsReducer from './reducers/zozzemonsSlice'
import userReducer from './reducers/userSlice'

export default configureStore({
  reducer: {
    zozzemon: zozzemonsReducer,
    user: userReducer
  }
})