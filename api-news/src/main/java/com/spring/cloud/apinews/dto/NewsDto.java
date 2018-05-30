package com.spring.cloud.apinews.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
  private String title;
  private String detail;
  private String firstName;
  private String lastName;
  private String category;
}
