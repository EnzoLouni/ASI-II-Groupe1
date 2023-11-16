import axios from 'axios';
import AxiosMockAdapter from 'axios-mock-adapter';

const axiosMockInstance = axios.create();
const axiosLiveInstance = axios.create();

console.log(process.env.REACT_APP_IS_MOCK, process.env.REACT_APP_IS_MOCK === 'true', process.env.REACT_APP_RPROXY)

export default process.env.REACT_APP_IS_MOCK === 'true' ? axiosMockInstance : axiosLiveInstance;
export const axiosMockAdapterInstance = new AxiosMockAdapter(axiosMockInstance, { delayResponse: 0 });