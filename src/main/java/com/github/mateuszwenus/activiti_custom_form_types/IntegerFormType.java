package com.github.mateuszwenus.activiti_custom_form_types;

import org.activiti.engine.form.AbstractFormType;

public class IntegerFormType extends AbstractFormType {

  @Override
  public String getName() {
    return "integer";
  }

  @Override
  public Object convertFormValueToModelValue(String propertyValue) {
    return Integer.valueOf(propertyValue);
  }

  @Override
  public String convertModelValueToFormValue(Object modelValue) {
    return modelValue != null ? modelValue.toString() : null;
  }
}