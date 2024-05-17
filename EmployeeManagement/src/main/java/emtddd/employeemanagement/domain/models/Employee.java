package emtddd.employeemanagement.domain.models;

import emtddd.employeemanagement.service.form.EmployeeForm;
import emtddd.sharedkernel.domain.base.DomainObjectId;
import emtddd.sharedkernel.domain.models.User;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "employee")
public class Employee extends User {

   @AttributeOverride(name="id", column = @Column(name="location_id"))
   private DomainObjectId locationId;

   public Employee(DomainObjectId location_id) {
      super();
      this.locationId = location_id;
   }

   public Employee() {
      super();
   }
   public Employee(EmployeeForm employeeForm){
      super(employeeForm.getFirst_name(), employeeForm.getLast_name(), employeeForm.getEmail());
      this.locationId = employeeForm.getLocation_id();
   }

   public DomainObjectId relocate(DomainObjectId new_location_id){
      this.locationId = new_location_id;
      return this.locationId;
   }

}
