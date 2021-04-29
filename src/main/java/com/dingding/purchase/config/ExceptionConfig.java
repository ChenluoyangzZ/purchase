package com.dingding.purchase.config;

import com.dingding.purchase.uitls.RespondResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMultipartFileException(MaxUploadSizeExceededException maxUploadSizeException) {
        return RespondResultUtils.errorMsg("上传图片大于500K,请压缩或者上传正确大小文件");
    }
}