package emtddd.sharedkernel.domain.base;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

public class UserID extends DomainObjectId{
    private UserID(){
        super(UserID.randomId(UserID.class).getId());
    }

    public UserID(@NonNull String id){
        super(id);
    }

}
