package emtddd.sharedkernel.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emtddd.sharedkernel.domain.base.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Address implements ValueObject {
    private String street;
    private int number;

    @JsonCreator
    public Address(@JsonProperty("street") String street,
                   @JsonProperty("number") int number) {
        this.street = street;
        this.number = number;
    }
}
