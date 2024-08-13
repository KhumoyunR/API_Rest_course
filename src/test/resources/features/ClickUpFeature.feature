Feature: This feature tests ClickUp API

  Scenario: Add a new folder, list, task and comment to the TestSpace
    Given ClickUp Space created and ready for further steps

    When User creates a new folder "New Folder" in the ClickUp Space and verifies its name
    And User creates a new list in the folder with name "New List"
    Then User verifies the name of the new list is "New List"
    And User adds a comment to the list
    Then User verifies the list comment is added
    And User creates a new task in the list with the name "New Task"
    Then User verifies the name of the new task is "New Task"
    And User adds a comment to the task
    Then User verifies the task comment is added
    And User removes the task from the list
    Then User verifies there is no any task in the list