package faeteam3.Notlage.notlage.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class BezugspersonsArentEqual extends RuntimeException {

    private static final long serialVersionUID = 1L;
}
