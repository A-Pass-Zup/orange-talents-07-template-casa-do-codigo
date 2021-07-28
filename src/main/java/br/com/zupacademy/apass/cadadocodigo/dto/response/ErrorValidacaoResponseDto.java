package br.com.zupacademy.apass.cadadocodigo.dto.response;

public class ErrorValidacaoResponseDto {
    private String field;
    private String message;

    public ErrorValidacaoResponseDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return this.field;
    }

    public String getMessage() {
        return this.message;
    }
}
