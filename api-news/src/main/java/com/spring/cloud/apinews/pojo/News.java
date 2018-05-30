package com.spring.cloud.apinews.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
  private int id;
  private String title;
  private String detail;
  private int authorId;
  private int categoryId;
}
