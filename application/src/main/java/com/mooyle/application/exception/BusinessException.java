package com.mooyle.application.exception;

import com.mooyle.application.result.CodeMsg;

public class BusinessException extends RuntimeException {

    private CodeMsg cm;

    public BusinessException(CodeMsg codeMsg){
        this.cm = codeMsg;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
