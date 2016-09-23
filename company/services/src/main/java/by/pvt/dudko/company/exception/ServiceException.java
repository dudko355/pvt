package by.pvt.dudko.company.exception;


public class ServiceException extends Exception {

    private String exception;

    public ServiceException(String exception) {
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
