package com.zukxu.flowable.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.flowable.bpmn.constants.BpmnXMLConstants;
import org.flowable.bpmn.model.Activity;
import org.flowable.bpmn.model.ExtensionElement;
import org.flowable.editor.language.json.converter.util.JsonConverterUtil;


/**
 * <p>
 * 扩展属性工具类
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 11:33
 */
public class ExtensionPropertiesUtil {

    public static void addExtensionPropertiesElement(JsonNode objectNode, Activity activity, String name) {
        JsonNode expansionNode = JsonConverterUtil.getProperty(name, objectNode);
        if(expansionNode instanceof TextNode) {
            if(StrUtil.isNotBlank(expansionNode.asText())) {
                ExtensionElement extensionElement = new ExtensionElement();
                extensionElement.setName(name);
                extensionElement.setNamespacePrefix(BpmnXMLConstants.FLOWABLE_EXTENSIONS_PREFIX);
                extensionElement.setNamespace(BpmnXMLConstants.FLOWABLE_EXTENSIONS_NAMESPACE);
                extensionElement.setElementText(expansionNode.asText());
                activity.addExtensionElement(extensionElement);
            }
        }
    }

}
