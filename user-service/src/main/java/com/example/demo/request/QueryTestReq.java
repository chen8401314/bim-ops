package com.example.demo.request;

import com.huagui.service.dto.PageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryTestReq extends PageReq {

    @ApiModelProperty(value = "名字")
    private String name;
}
