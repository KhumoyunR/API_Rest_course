package apiTask.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ClickUpClient {

    private static RequestSpecification clickUpSpec(){
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "pk_82550286_SB6H232CZGEG4GL1FYVX46Y7HP6HWYDU");
    }

    public static Response getSpaceInfo(String spaceId){
        return RestAssured
                .given(clickUpSpec())
                .when()
                .get("https://api.clickup.com/api/v2/space/" + spaceId)
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response CreateNewFolder(String name, String spaceId){

        return RestAssured
                .given(clickUpSpec())
                .body("{\"name\": \"" + name + "\"}")
                .when()
                .post("https://api.clickup.com/api/v2/space/" + spaceId + "/folder")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response CreateNewList(String name, String folderId){

        return RestAssured
                .given(clickUpSpec())
                .body("{\"name\": \"" + name + "\"}")
                .when()
                .post("https://api.clickup.com/api/v2/folder/" + folderId + "/list")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }


    public static Response CreateListComment(String text, String listId){

        return RestAssured
                .given(clickUpSpec())
                .body("{\"comment_text\": \"" + text + "\"}")
                .when()
                .post("https://api.clickup.com/api/v2/list/" + listId + "/comment")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response CheckListComment(String listId){

        return RestAssured
                .given(clickUpSpec())
                .when()
                .get("https://api.clickup.com/api/v2/list/" + listId + "/comment")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response CreateNewTask(String name, String listId){

        return RestAssured
                .given(clickUpSpec())
                .body("{\"name\": \"" + name + "\"}")
                .when()
                .post("https://api.clickup.com/api/v2/list/" + listId + "/task")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response CreateTaskComment(String text, String taskId){

        return RestAssured
                .given(clickUpSpec())
                .body("{\"comment_text\": \"" + text + "\"}")
                .when()
                .post("https://api.clickup.com/api/v2/task/" + taskId + "/comment")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response CheckTaskComment(String taskId){

        return RestAssured
                .given(clickUpSpec())
                .when()
                .get("https://api.clickup.com/api/v2/task/" + taskId + "/comment")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response deleteTask(String taskId){

        return RestAssured
                .given(clickUpSpec())
                .when()
                .delete("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .statusCode(204)
                .extract()
                .response();
    }

    public static Response checkTasks(String listId){

        return RestAssured
                .given(clickUpSpec())
                .when()
                .get("https://api.clickup.com/api/v2/list/" + listId + "/task")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response deleteFolder(String folderId){

        return RestAssured
                .given(clickUpSpec())
                .when()
                .delete("https://api.clickup.com/api/v2/folder/" + folderId)
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }
}
