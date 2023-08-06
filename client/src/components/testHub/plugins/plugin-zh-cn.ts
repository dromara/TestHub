import checkZhCn from "./check/i18n/check-zh-cn";
import checkObjZhCn from "./checkObj/i18n/checkObj-zh-cn";
import httpZhCn from "./http/i18n/http-zh-cn";
import sleepZhCn from "./sleep/i18n/sleep-zh-cn";
import sqlZhCn from "./sql/i18n/sql-zh-cn";

export default {
    ...httpZhCn,
    ...sqlZhCn,
    ...checkObjZhCn,
    ...checkZhCn,
    ...sleepZhCn
}