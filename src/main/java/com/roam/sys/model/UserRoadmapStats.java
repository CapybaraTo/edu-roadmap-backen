package com.roam.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

//用户路线图进度条
@Data
@ApiModel(value="UserRoadmapStats对象", description="用户路线图进展数据")
public class UserRoadmapStats implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "路线图Title")
    private String title;

    @ApiModelProperty(value = "路线图Id")
    private String id;

    @ApiModelProperty(value = "done状态知识点数量")
    private Integer done;

    @ApiModelProperty(value = "learning状态知识点数量")
    private Integer learning;

    @ApiModelProperty(value = "skipped状态知识点数量")
    private Integer skipped;

    @ApiModelProperty(value = "路线图总知识点数")
    private Integer total;

    @ApiModelProperty(value = "最新学习时间")
    private String updatedAt;
}
