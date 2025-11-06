package com.Cliente.cliente_service.api;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String,Object>> notFound(HttpServletRequest req, NoSuchElementException ex){
        return ResponseEntity.status(404).body(Map.of(
                "type","https://acme.local/errors/recurso-nao-encontrado",
                "title","Recurso não encontrado",
                "status",404,
                "detail", ex.getMessage(),
                "instance", req.getRequestURI()
        ));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> badRequest(HttpServletRequest req, MethodArgumentNotValidException ex){
        var details = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField()+": "+f.getDefaultMessage()).collect(Collectors.joining("; "));
        return ResponseEntity.status(400).body(Map.of(
                "type","https://acme.local/errors/validacao",
                "title","Erro de validação",
                "status",400,
                "detail", details,
                "instance", req.getRequestURI()
        ));
    }
}
