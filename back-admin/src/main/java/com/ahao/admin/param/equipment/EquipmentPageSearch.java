package com.ahao.admin.param.equipment;

import com.ahao.admin.utils.PageParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: EquipmentPageSearch
 * @Author: ahao
 * @Date: 2023/5/7 16:56
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class EquipmentPageSearch extends PageParams implements Serializable {
    public static final long SerialVersionUID = 1L;

    @JsonProperty("equipment_name")
    private String equipmentName;

}
