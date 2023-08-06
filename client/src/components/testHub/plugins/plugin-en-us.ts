import checkEnUs from "./check/i18n/check-en-us";
import checkObjEnUs from "./checkObj/i18n/checkObj-en-us";
import httpEnUs from "./http/i18n/http-en-us";
import sleepEnUs from "./sleep/i18n/sleep-en-us";
import sqlEnUs from "./sql/i18n/sql-en-us";

export default {
    ...httpEnUs,
    ...sqlEnUs,
    ...checkObjEnUs,
    ...checkEnUs,
    ...sleepEnUs
}