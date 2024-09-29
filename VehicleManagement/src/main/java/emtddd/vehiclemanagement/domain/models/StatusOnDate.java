package emtddd.vehiclemanagement.domain.models;

import emtddd.sharedkernel.domain.base.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class StatusOnDate extends AbstractEntity<StatusOnDateID>{
    private LocalDate date;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;


    public StatusOnDate(LocalDate date, Status status, Vehicle vehicle) {
        super(StatusOnDateID.randomId(StatusOnDateID.class));
        this.date = date;
        this.status = status;
        this.vehicle = vehicle;
    }

    public StatusOnDate(){
        super(StatusOnDateID.randomId(StatusOnDateID.class));
        this.date = null;
        this.status = null;
        this.vehicle = null;
    }
}
