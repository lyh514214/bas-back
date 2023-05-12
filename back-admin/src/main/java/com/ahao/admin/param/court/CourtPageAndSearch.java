package com.ahao.admin.param.court;

import com.ahao.admin.utils.PageParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: CourtPageAndSearch
 * @Author: ahao
 * @Date: 2023/5/3 16:23
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class CourtPageAndSearch extends PageParams implements Serializable {
    public static final long SerialVersionUID = 1L;

    @JsonProperty("court_name")
    private String courtName;
    @JsonProperty("court_category_id")
    private Integer courtCategoryId;
    @JsonProperty("court_region_id")
    private Integer courtRegionId;


}
