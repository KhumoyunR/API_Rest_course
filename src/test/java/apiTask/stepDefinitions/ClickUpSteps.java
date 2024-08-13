package apiTask.stepDefinitions;

import apiTask.domain.Space;
import apiTask.helpers.TestCaseContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.List;

import static apiTask.clients.ClickUpClient.*;
import static apiTask.constants.Taskconstants.*;
import static apiTask.helpers.TestCaseContext.*;

public class ClickUpSteps {

    @Given("ClickUp Space created and ready for further steps")
    public void getSpace(){
        Response response = getSpaceInfo(SPACE_ID);
        Space space = response.as(Space.class);
        TestCaseContext.setSpace(space);

        Assertions.assertThat(space.getId())
                .as("The Space ID is correct")
                .isEqualTo(SPACE_ID);

        Assertions.assertThat(space.getName())
                .as("The Space NAME is correct")
                .isEqualTo(SPACE_NAME);
    }

    @When("User creates a new folder {string} in the ClickUp Space and verifies its name")
    public void addNewFolderAndVerify(String name){
        Response response = CreateNewFolder(name, SPACE_ID);
        Space folderResponse = response.as(Space.class);
        TestCaseContext.setFolder(folderResponse);
        String folderName = TestCaseContext.getFolder().getName();

        Assertions.assertThat(folderName)
                .as("The Folder NAME is correct")
                .isEqualTo(name);
    }

    @And("User creates a new list in the folder with name {string}")
    public void addNewList(String name){
        String folderId = TestCaseContext.getFolder().getId();
        Response response = CreateNewList(name, folderId);
        Space listResponse = response.as(Space.class);
        listResponse.setParentId(folderId);
        TestCaseContext.setList(listResponse);
    }

    @Then("User verifies the name of the new list is {string}")
    public void verifyListNameAndFolder(String name){
        String listName = TestCaseContext.getList().getName();
        Assertions.assertThat(listName)
                .as("The List NAME is correct")
                .isEqualTo(name);

        String checkId = getList().getParentId();
        String folderId = TestCaseContext.getFolder().getId();
        Assertions.assertThat(folderId)
                .as("The List is in the correct folder")
                .isEqualTo(checkId);

    }

    @And("User adds a comment to the list")
    public void addListComment(){
        String commentText = "Comment to the new list is added.";
        String listId = TestCaseContext.getList().getId();
        Response response = CreateListComment(commentText, listId);
    }

    @Then("User verifies the list comment is added")
    public void VerifyListComment(){
        String commentText = "Comment to the new list is added.";
        String listId = TestCaseContext.getList().getId();
        Response response = CheckListComment(listId);
        String responseCommentText = response.jsonPath().getString("comments[0].comment_text");

        Assertions.assertThat(responseCommentText)
                .as("New List comment is added")
                .isEqualTo(commentText);
    }

    @And("User creates a new task in the list with the name {string}")
    public void addNewTask(String name){
        String listId = TestCaseContext.getList().getId();
        Response response = CreateNewTask(name, listId);
        Space taskResponse = response.as(Space.class);
        TestCaseContext.setTask(taskResponse);
    }

    @Then("User verifies the name of the new task is {string}")
    public void verifyTaskName(String name){
        String taskName = TestCaseContext.getTask().getName();
        Assertions.assertThat(taskName)
                .as("The List NAME is correct")
                .isEqualTo(name);
    }

    @And("User adds a comment to the task")
    public void addTaskComment(){
        String commentText = "Comment to the new task is added.";
        String taskId = TestCaseContext.getTask().getId();
        Response response = CreateTaskComment(commentText, taskId);
    }

    @Then("User verifies the task comment is added")
    public void VerifyTaskComment(){
        String commentText = "Comment to the new task is added.";
        String taskId = TestCaseContext.getTask().getId();
        Response response = CheckTaskComment(taskId);
        String responseCommentText = response.jsonPath().getString("comments[0].comment_text");

        Assertions.assertThat(responseCommentText)
                .as("New Task comment is added")
                .isEqualTo(commentText);
    }

    @And("User removes the task from the list")
    public void removeTask(){
        String taskId = TestCaseContext.getTask().getId();
        Response response = deleteTask(taskId);
    }

    @Then("User verifies there is no any task in the list")
    public void verifyRemove(){
        String listId = TestCaseContext.getList().getId();
        String taskId = TestCaseContext.getTask().getId();
        Response response = checkTasks(listId);

        List<Object> taskIds = response.jsonPath().getList("tasks.id");
        boolean taskExists = taskIds.contains(taskId);
        Assertions.assertThat(taskExists)
                .as("Task should not exist in the list")
                .isFalse();
    }
}
