package com.github.mateuszwenus.activiti_custom_form_types;

import java.util.HashMap;
import java.util.Map;

import org.activiti.bpmn.model.FormProperty;
import org.activiti.bpmn.model.FormValue;
import org.activiti.engine.form.AbstractFormType;
import org.activiti.engine.impl.form.FormTypes;

public class CustomFormTypes extends FormTypes {

  @Override
  public AbstractFormType parseFormPropertyType(FormProperty formProperty) {
    if (SelectFromRangeFormType.NAME.equals(formProperty.getType())) {
      return parseSelectWithRangeFormPropertyType(formProperty);
    } else {
      return super.parseFormPropertyType(formProperty);
    }
  }

  private AbstractFormType parseSelectWithRangeFormPropertyType(FormProperty formProperty) {
    Map<String, String> map = new HashMap<String, String>();
    for (FormValue fv : formProperty.getFormValues()) {
      map.put(fv.getId(), fv.getName());
    }
    int from = Integer.parseInt(map.get("from"));
    int to = Integer.parseInt(map.get("to"));
    return new SelectFromRangeFormType(from, to);
  }
}
