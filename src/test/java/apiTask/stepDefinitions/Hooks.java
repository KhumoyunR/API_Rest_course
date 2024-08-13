package apiTask.stepDefinitions;

import apiTask.helpers.TestCaseContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static apiTask.clients.ClickUpClient.deleteFolder;

public class Hooks {

    @Before
    public void beforeHook(){
        TestCaseContext.init();
        System.out.println("Scenario has started");
    }

    @After
    public void afterHook(){
        deleteFolder(TestCaseContext.getFolder().getId());
        System.out.println("Scenario has ended");
    }
}
