package com.github.mateuszwenus.activiti_custom_form_types;

import org.activiti.engine.form.AbstractFormType;

public class SelectFromRangeFormType extends AbstractFormType {

  public static final String NAME = "selectFromRange";

  private final int from;
  private final int to;

  public SelectFromRangeFormType(int from, int to) {
    this.from = from;
    this.to = to;
  }

  public int getFrom() {
    return from;
  }

  public int getTo() {
    return to;
  }

  @Override
  public String getName() {
    return NAME;
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
