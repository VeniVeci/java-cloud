package com.zukxu.common.core.base;

import lombok.Data;

import java.util.Map;

/**
 * <p>
 * 查询实体
 * </p>
 *
 * @author xupu
 * @since 2022/6/21 10:57:44
 */
@Data
public class QueryEntity {

    private Integer pageNum;

    private Integer pageSize;

    private String keyWord;

    private Map<String, Object> params;

}
