package com.example.miniproject;

import java.util.regex.Pattern;

public class PasswordValidator {
  private static final String PASSWORD_PATTERN = 
      "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,12}$";

  private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

  public static boolean validate(final String password) {
    return pattern.matcher(password).matches();
  }
}
