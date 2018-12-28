/**
 * Copyright 2006-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 *
 * @author Jeff Butler
 * @author xionghui
 *
 */
public class SelectByExampleWithoutBLOBsElementGenerator extends AbstractXmlElementGenerator {

  public SelectByExampleWithoutBLOBsElementGenerator() {
    super();
  }

  @Override
  public void addElements(XmlElement parentElement) {
    XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

    answer.addAttribute(new Attribute("id", //$NON-NLS-1$
        this.introspectedTable.getSelectByExampleStatementId()));
    answer.addAttribute(new Attribute("resultMap", this.introspectedTable.getBaseResultMapId())); //$NON-NLS-1$
    answer.addAttribute(new Attribute("parameterType", "java.util.Map")); //$NON-NLS-1$

    this.context.getCommentGenerator().addComment(answer);

    answer.addElement(new TextElement("select")); //$NON-NLS-1$
    XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
    ifElement.addAttribute(new Attribute("test", "example != null and example.distinct")); //$NON-NLS-1$ //$NON-NLS-2$
    ifElement.addElement(new TextElement("distinct")); //$NON-NLS-1$
    answer.addElement(ifElement);

    StringBuilder sb = new StringBuilder();
    if (stringHasValue(this.introspectedTable.getSelectByExampleQueryId())) {
      sb.append('\'');
      sb.append(this.introspectedTable.getSelectByExampleQueryId());
      sb.append("' as QUERYID,"); //$NON-NLS-1$
      answer.addElement(new TextElement(sb.toString()));
    }
    answer.addElement(this.getBaseColumnListElement());

    sb.setLength(0);
    sb.append("from "); //$NON-NLS-1$
    // sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
    answer.addElement(new TextElement(sb.toString()));
    answer.addElement(this.getTableNameIncludeElement());
    answer.addElement(this.getExampleIncludeElement());

    ifElement = new XmlElement("if"); //$NON-NLS-1$
    ifElement
        .addAttribute(new Attribute("test", "example != null and example.orderByClause != null")); //$NON-NLS-1$ //$NON-NLS-2$
    ifElement.addElement(new TextElement("order by ${example.orderByClause}")); //$NON-NLS-1$
    answer.addElement(ifElement);

    if (this.context.getPlugins().sqlMapSelectByExampleWithoutBLOBsElementGenerated(answer,
        this.introspectedTable)) {
      parentElement.addElement(answer);
    }
  }
}