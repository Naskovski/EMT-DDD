package emtddd.sharedkernel.domain.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidIdException extends RuntimeException{
    public InvalidIdException(String message){
        super(message);
    }
}
