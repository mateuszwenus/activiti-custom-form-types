package com.github.mateuszwenus.activiti_custom_form_types;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Before;

public abstract class AbstractActivitiTest {

  protected RepositoryService repositoryService;
  protected RuntimeService runtimeService;
  protected TaskService taskService;
  protected FormService formService;

  protected abstract ActivitiRule getActivitiRule();

  @Before
  public void init() {
    repositoryService = getActivitiRule().getRepositoryService();
    runtimeService = getActivitiRule().getRuntimeService();
    taskService = getActivitiRule().getTaskService();
    formService = getActivitiRule().getFormService();
  }

  protected ProcessInstance deployAndStartProcess(String processFilename, String processKey) throws FileNotFoundException {
    String resourceName = processFilename.substring(processFilename.lastIndexOf('/') + 1).replace(".bpmn", ".bpmn20.xml");
    repositoryService.createDeployment().addInputStream(resourceName, new FileInputStream(processFilename)).deploy();
    return runtimeService.startProcessInstanceByKey(processKey);
  }

  protected Task findCurrentTask(ProcessInstance processInstance) {
    return taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  }

  protected void completeCurrentTask(ProcessInstance processInstance) {
    Task task = findCurrentTask(processInstance);
    taskService.complete(task.getId());
  }
}
