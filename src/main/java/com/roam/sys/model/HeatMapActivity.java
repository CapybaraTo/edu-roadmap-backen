package com.roam.sys.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//用户统计数据
@Data
@ApiModel(value="UserHeatMap对象", description="用户heatmap数据")
public class HeatMapActivity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "日期")
    private String date;

    @ApiModelProperty(value = "数量")
    private Integer count;

}


