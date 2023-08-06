package org.dromara.testhub.framework.exceptionHandler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SaTokenException;
import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.common.exception.AppException;
import org.dromara.testhub.framework.exceptionHandler.ErrorCode;
import org.dromara.testhub.framework.exceptionHandler.ValidMsg;
import org.dromara.testhub.framework.web.ResultResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 异常处理器
 *
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2021/4/7 上午9:35
 * @Copyright © 女神帮
 */
@RestControllerAdvice
public class AppExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());


    public AppExceptionHandler() {
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(AppException.class)
    public ResultResponse handleRRException(AppException e) {
        logger.error(e.getMessage(), e);
        return ResultResponse.error(StringUtils.isBlank(e.getCode()) ? "500" : e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultResponse handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultResponse.error("404", "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResultResponse handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return ResultResponse.error(ErrorCode.DUPLICATION, "数据库中已存在该记录");
    }

//    @ExceptionHandler(AuthorizationException.class)
//    public ResultResponse handleAuthorizationException(AuthorizationException e) {
//        logger.error(e.getMessage(), e);
//
//        return ResultResponse.error( serve + ".401", "没有权限，请联系管理员授权");
//    }

    @ExceptionHandler(Exception.class)
    public ResultResponse handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultResponse.error("500", e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultResponse handleException(RuntimeException e) {
        logger.error(e.getMessage(), e);
        return ResultResponse.error("500", e.getMessage());
    }
    @ExceptionHandler(SaTokenException.class)
    public ResultResponse handleException(SaTokenException e) {
        logger.error(e.getMessage(), e);
        return ResultResponse.error("sa"+e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NotRoleException.class)
    public ResultResponse handleException(NotRoleException e) {
        logger.error(e.getMessage(), e);
        return ResultResponse.error(ErrorCode.FORBIDDEN, "没有权限，请联系管理员授权");
    }
    @ExceptionHandler(NotPermissionException.class)
    public ResultResponse handleException(NotPermissionException e) {
        logger.error(e.getMessage(), e);
        return ResultResponse.error(ErrorCode.FORBIDDEN, "没有权限，请联系管理员授权");
    }
    @ExceptionHandler(NotLoginException.class)
    public ResultResponse handleException(NotLoginException e) {
        logger.error(e.getMessage(), e);
        return ResultResponse.error(ErrorCode.UNAUTHORIZED, "需要登陆");
    }

    /**
     * 处理请求对象属性不满足校验规则的异常信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultResponse<Void> exception(HttpServletRequest request, MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<ValidMsg> errorList = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            errorList.add(new ValidMsg(error.getObjectName(), error.getField(), error.getDefaultMessage()));
        }
        logger.error(exception.getMessage(), exception);
        return new ResultResponse<Void>(ErrorCode.DATA_ILLEGAL, listToJson(errorList));
    }

    /**
     * 参数类型数据异常
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResultResponse<Void> exception(HttpServletRequest request, MethodArgumentTypeMismatchException exception) {
        logger.error(exception.getMessage(), exception);
        List<ValidMsg> errorList = new ArrayList<>();
        errorList.add(new ValidMsg("", exception.getName(), exception.getMessage()));
        return new ResultResponse<Void>(ErrorCode.DATA_ILLEGAL, listToJson(errorList));
    }

    /**
     * 丢失参数异常
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResultResponse<Void> exception(HttpServletRequest request, MissingServletRequestParameterException exception) {
        List<ValidMsg> errorList = new ArrayList<>();
        errorList.add(new ValidMsg("", exception.getParameterName(), exception.getMessage()));
        logger.error(exception.getMessage(), exception);
        return new ResultResponse<Void>(ErrorCode.DATA_MISSING, listToJson(errorList));
    }

    ///**
    // * 处理请求单个参数不满足校验规则的异常信息
    // */
    //@ExceptionHandler(value = ConstraintViolationException.class)
    //public ResultResponse<Void> constraintViolationExceptionHandler(HttpServletRequest request,
    //                                                                ConstraintViolationException exception) {
    //    logger.error(exception.getMessage(), exception);
    //    return ResultResponse.error( serve + "." + ErrorCode.DATA_ILLEGAL, exception.getMessage());
    //}

    /**
     * 参数无效的异常信息处理。
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultResponse<Void> handleIllegalArgumentException(HttpServletRequest request,
                                                               IllegalArgumentException exception) {
        logger.error(exception.getMessage(), exception);
        return ResultResponse.error(ErrorCode.DATA_ILLEGAL, exception.getMessage());
    }

    /**
     * 消息不可读异常
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResultResponse<Void> handleIllegalArgumentException(HttpServletRequest request,
                                                               HttpMessageNotReadableException exception) {
        logger.error(exception.getMessage(), exception);
        return ResultResponse.error( ErrorCode.DATA_ILLEGAL, exception.getMessage());
    }

    private String listToJson(List<ValidMsg> errorList) {

        String msg = "数据验证错误";
        try {
            msg = JSONObject.toJSONString(errorList);

        } catch (Exception e) {
        }
        return msg;
    }
}
