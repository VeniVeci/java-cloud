package com.zukxu.design.test1;

import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022/6/9 16:54:22
 */
@Component("B")
public class ResourceB implements ResourceStrategy {

    @Override
    public String orderInformation(String id) {
        System.out.println("策略选择：Strategy B");
        return "B";
    }

}