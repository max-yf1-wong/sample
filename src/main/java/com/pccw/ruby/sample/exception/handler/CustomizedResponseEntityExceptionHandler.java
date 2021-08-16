package com.pccw.ruby.sample.exception.handler;

import com.pccw.ruby.common.exception.handler.CommonResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler
        extends CommonResponseEntityExceptionHandler {}
