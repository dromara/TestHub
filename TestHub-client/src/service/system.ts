import { RuleParamResDto } from "@/typings";
import createRequest from "./base";
const getId = createRequest<void, string>('/api/system/getId', { method: 'get' });
const getCode = createRequest<{ type: string }, string>('/api/system/getCode/:type', { method: 'post' });
const testService = createRequest<void, void>('/api/system', { errorLevel: false });
const systemStop = createRequest<void, void>('/api/system/stop', { errorLevel: false, method: 'post' });
const testApiSmooth = createRequest<void, void>('/api/system/get-version-a', { errorLevel: false, method: 'get' });


const paramsJson2Xml = createRequest<{ info: string }, string>('/api/system/paramsJson2Xml', { method: 'post' });
const paramsXml2Json = createRequest<{ info: string }, RuleParamResDto[]>('/api/system/paramsXml2Json', { method: 'post' });


export default {
    getId,
    getCode,
    testService,
    systemStop,
    testApiSmooth,
    paramsJson2Xml,
    paramsXml2Json
}