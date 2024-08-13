package apiTask.helpers;

import apiTask.domain.Space;

public class TestCaseContext {

    private static Space space;
    private static Space folder;
    private static Space list;
    private static Space task;

    public static void init(){
        space = null;
        folder = null;
        list = null;
        task = null;
    }

    public static Space getSpace() {
        return space;
    }

    public static void setSpace(Space space) {
        TestCaseContext.space = space;
    }

    public static Space getFolder() {
        return folder;
    }

    public static void setFolder(Space folder) {
        TestCaseContext.folder = folder;
    }

    public static Space getList() {
        return list;
    }

    public static void setList(Space list) {
        TestCaseContext.list = list;
    }

    public static Space getTask() {
        return task;
    }

    public static void setTask(Space task) {
        TestCaseContext.task = task;
    }
}
