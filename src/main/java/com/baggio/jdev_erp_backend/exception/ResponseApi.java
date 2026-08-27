package com.baggio.jdev_erp_backend.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseApi {

  private Date localDateTime;
  private int status;
  private String error;
  private String message;
  private String path;

}
