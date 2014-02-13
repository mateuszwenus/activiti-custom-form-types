package com.github.mateuszwenus.activiti_custom_form_types;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProcessTestIntegerFormType extends AbstractActivitiTest {

  @Rule
  public ActivitiRule activitiRule = new ActivitiRule("activiti-integer.cfg.xml");

  @Override
  protected ActivitiRule getActivitiRule() {
    return activitiRule;
  }

  @Test
  public void shouldHaveTaskWithIntegerFormProperty() throws Exception {
    // given
    ProcessInstance processInstance = deployAndStartProcess("src/main/resources/diagram/integer-form-type.bpmn", "integer_form_type");
    Task task = findCurrentTask(processInstance);
    // when
    TaskFormData taskFormData = formService.getTaskFormData(task.getId());
    // then
    assertThat(taskFormData, is(notNullValue()));
    assertThat(taskFormData.getFormProperties(), is(notNullValue()));
    assertThat(taskFormData.getFormProperties().size(), is(1));
    assertThat(taskFormData.getFormProperties().get(0), is(notNullValue()));
    assertThat(taskFormData.getFormProperties().get(0).getType(), instanceOf(IntegerFormType.class));
  }
}