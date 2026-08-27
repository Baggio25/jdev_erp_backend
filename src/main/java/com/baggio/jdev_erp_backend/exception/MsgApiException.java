package com.baggio.jdev_erp_backend.exception;

import org.springframework.http.HttpStatus;

public class MsgApiException extends RuntimeException{

  private HttpStatus status;

  public MsgApiException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public MsgApiException(String message) {
    super(message);
    this.status = HttpStatus.OK;
  }

  public HttpStatus getStatus() {
    return status;
  }

}
