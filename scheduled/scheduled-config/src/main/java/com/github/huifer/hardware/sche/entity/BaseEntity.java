package com.github.huifer.hardware.sche.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class BaseEntity {
  @Column(name = "company_id")
  private String companyId;

  @Column(name = "create_user_id")
  private String createUserId;

  @Column(name = "update_time")
  private LocalDateTime updateTime;

  @Column(name = "create_time")
  private LocalDateTime createTime;

  @Column(name = "update_user_id")
  private String updateUserId;

  @Column(name = "deleted")
  private Boolean deleted;

  @Version
  @Column(name = "version")
  private Long version;
}
