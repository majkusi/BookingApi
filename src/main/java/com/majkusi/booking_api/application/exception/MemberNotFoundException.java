package com.majkusi.booking_api.application.exception;

public class MemberNotFoundException extends RuntimeException {
  public MemberNotFoundException(String message) {
    super(message);
  }
}
