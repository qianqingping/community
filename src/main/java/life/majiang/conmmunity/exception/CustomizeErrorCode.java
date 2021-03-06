package life.majiang.conmmunity.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你的问题不在了，换一个试试？");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message){
        this.message= message;
    }
}
