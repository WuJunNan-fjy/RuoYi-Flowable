package com.ruoyi.flowable.api.category.request;

import lombok.Data;

@Data
public class FlwCategorySaveReq {
    private Long id;
    private String code;
    private String name;
    private Long sort;
    private Long status;
    private String remark;
}
