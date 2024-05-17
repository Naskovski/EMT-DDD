package emtddd.employeemanagement.domain.models;

import emtddd.sharedkernel.domain.base.AbstractEntity;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.base.UserID;
import emtddd.sharedkernel.domain.models.User;
import jakarta.persistence.Entity;


public class Employee extends User {

   private DomainObjectId location_id;

   public Employee(DomainObjectId location_id) {
      this.location_id = location_id;
   }

   public DomainObjectId relocate(DomainObjectId new_location_id){
      this.location_id = new_location_id;
      return this.location_id;
   }

}
