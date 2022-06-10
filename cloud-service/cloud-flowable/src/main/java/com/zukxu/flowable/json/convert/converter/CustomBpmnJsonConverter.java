package com.zukxu.flowable.json.convert.converter;

import org.flowable.editor.language.json.converter.BpmnJsonConverter;

/**
 * @program: prodflow
 * @description:
 * @author: Bruce.Liu
 * @create: 2021-05-08 08:52
 **/
/**
 * <p>
 *  自定义Bpmn json转换类
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 11:29
 */
public class CustomBpmnJsonConverter extends BpmnJsonConverter {
    static {
        ExtendUserTaskJsonConverter.customFillTypes(convertersToBpmnMap, convertersToJsonMap);
        ExtendCallActivityJsonConverter.customFillTypes(convertersToBpmnMap, convertersToJsonMap);
        ExtendSubProcessJsonConverter.customFillTypes(convertersToBpmnMap, convertersToJsonMap);
    }
}
