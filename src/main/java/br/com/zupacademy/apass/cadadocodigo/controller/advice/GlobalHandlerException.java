package br.com.zupacademy.apass.cadadocodigo.controller.advice;

import br.com.zupacademy.apass.cadadocodigo.dto.response.ErrorValidacaoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalHandlerException {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidacaoResponseDto>> handlerException(MethodArgumentNotValidException exception) {
        List<ErrorValidacaoResponseDto> erros = new ArrayList();
        exception.getFieldErrors().forEach(e-> {
            erros.add(new ErrorValidacaoResponseDto(e.getField(), this.messageSource.getMessage(e, LocaleContextHolder.getLocale())));
        });

        return ResponseEntity.badRequest().body(erros);
    }
}
