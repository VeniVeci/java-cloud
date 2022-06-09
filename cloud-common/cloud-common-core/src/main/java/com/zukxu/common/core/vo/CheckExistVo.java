package com.zukxu.common.core.vo;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * <p>
 * 判断字段是否存在的参数VO
 * </p>
 *
 * @author xupu
 * @since 2022/6/9 17:53
 */
@Data
@Accessors(chain = true)
public class CheckExistVo {

    //主键
    private String id;

    //字段名
    private String field;

    //字段值
    private String fieldValue;

    //字段备注
    private String fieldName;

}
