package exception;

public class NumeroMaiorQuePermitidoException extends RuntimeException{
    private String msg;

    public NumeroMaiorQuePermitidoException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
