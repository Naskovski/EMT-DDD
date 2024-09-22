package emtddd.sharedkernel.domain.valueobjects;

import emtddd.sharedkernel.domain.base.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Password implements ValueObject {
    private String password;
}
