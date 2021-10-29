package com.temzu.market.corelib.exceptions;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MessageError {

  private int status;
  private String message;
  private LocalDateTime timestamp;

  public MessageError(int status, String message) {
    this.status = status;
    this.message = message;
    this.timestamp = LocalDateTime.now();
  }
}
