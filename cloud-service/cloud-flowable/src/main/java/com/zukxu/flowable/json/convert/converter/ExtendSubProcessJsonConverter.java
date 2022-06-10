package com.zukxu.flowable.json.convert.converter;

import com.fasterxml.jackson.databind.JsonNode;
import org.flowable.bpmn.model.*;
import org.flowable.editor.language.json.converter.BaseBpmnJsonConverter;
import org.flowable.editor.language.json.converter.BpmnJsonConverterContext;
import org.flowable.editor.language.json.converter.BpmnJsonConverterUtil;
import org.flowable.editor.language.json.converter.SubProcessJsonConverter;

import java.util.Map;

/**
 * <p>
 * 子流程转换类
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 11:37
 */
public class ExtendSubProcessJsonConverter extends SubProcessJsonConverter {

    public static void customFillTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
                                       Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {

        fillJsonTypes(convertersToBpmnMap);
        fillBpmnTypes(convertersToJsonMap);
    }

    public static void fillJsonTypes(Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
        convertersToBpmnMap.put(STENCIL_SUB_PROCESS, ExtendSubProcessJsonConverter.class);
        convertersToBpmnMap.put(STENCIL_COLLAPSED_SUB_PROCESS, ExtendSubProcessJsonConverter.class);
    }

    public static void fillBpmnTypes(Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
        convertersToJsonMap.put(SubProcess.class, ExtendSubProcessJsonConverter.class);
        convertersToJsonMap.put(Transaction.class, ExtendSubProcessJsonConverter.class);
    }

    @Override
    protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap,
                                               BpmnJsonConverterContext converterContext) {
        FlowElement flowElement = super.convertJsonToElement(elementNode, modelNode, shapeMap, converterContext);
        GraphicInfo graphicInfo = model.getGraphicInfo(BpmnJsonConverterUtil.getElementId(elementNode));
        graphicInfo.setExpanded(!STENCIL_COLLAPSED_SUB_PROCESS.equals(BpmnJsonConverterUtil.getStencilId(elementNode))); //default is null!
        return flowElement;
    }

}
