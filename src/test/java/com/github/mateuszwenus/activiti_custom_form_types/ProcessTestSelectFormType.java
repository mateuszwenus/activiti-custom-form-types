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
public class ProcessTestSelectFormType extends AbstractActivitiTest {

  private String filename = "src/main/resources/diagram/select-form-type.bpmn";
  private String processKey = "select_form_type";

  @Rule
  public ActivitiRule activitiRule = new ActivitiRule("activiti-select.cfg.xml");

  @Override
  public ActivitiRule getActivitiRule() {
    return activitiRule;
  }

  @Test
  public void firstTaskShouldHaveSelectZeroToTen() throws Exception {
    // given
    ProcessInstance processInstance = deployAndStartProcess(filename, processKey);
    Task task = findCurrentTask(processInstance);
    // when
    TaskFormData taskFormData = formService.getTaskFormData(task.getId());
    // then
    assertThat(taskFormData, is(notNullValue()));
    assertThat(taskFormData.getFormProperties(), is(notNullValue()));
    assertThat(taskFormData.getFormProperties().size(), is(1));
    assertThat(taskFormData.getFormProperties().get(0), is(notNullValue()));
    assertThat(taskFormData.getFormProperties().get(0).getType(), instanceOf(SelectFromRangeFormType.class));
    SelectFromRangeFormType select = (SelectFromRangeFormType) taskFormData.getFormProperties().get(0).getType();
    assertThat(select.getFrom(), is(0));
    assertThat(select.getTo(), is(10));
  }

  @Test
  public void secondTaskShouldHaveSelectMinusFiveToFive() throws Exception {
    // given
    ProcessInstance processInstance = deployAndStartProcess(filename, processKey);
    completeCurrentTask(processInstance);
    Task secondTask = findCurrentTask(processInstance);
    // when
    TaskFormData taskFormData = formService.getTaskFormData(secondTask.getId());
    // then
    assertThat(taskFormData, is(notNullValue()));
    assertThat(taskFormData.getFormProperties(), is(notNullValue()));
    assertThat(taskFormData.getFormProperties().size(), is(1));
    assertThat(taskFormData.getFormProperties().get(0), is(notNullValue()));
    assertThat(taskFormData.getFormProperties().get(0).getType(), instanceOf(SelectFromRangeFormType.class));
    SelectFromRangeFormType select = (SelectFromRangeFormType) taskFormData.getFormProperties().get(0).getType();
    assertThat(select.getFrom(), is(-5));
    assertThat(select.getTo(), is(5));
  }
}