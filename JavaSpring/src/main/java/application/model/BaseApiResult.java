package application.model;

public class BaseApiResult {
    private boolean isSuccess;
    private String message;

    public boolean isSuccess(){
        return isSuccess;
    }
    public void setSuccess(boolean success){
        isSuccess = success;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
