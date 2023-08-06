import createRequest from "./base";
const getCode = createRequest<{ type: string }, string>('/api/system/getCode/:type', { method: 'post' });
const testService = createRequest<void, void>('/api/system', { errorLevel: false });
const systemStop = createRequest<void, void>('/api/system/stop', { errorLevel: false, method: 'post' });
const testApiSmooth = createRequest<void, void>('/api/system/get-version-a', { errorLevel: false, method: 'get' });

export default {
    getCode,
    testService,
    systemStop,
    testApiSmooth
}