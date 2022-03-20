package uniProject.carShowroomManagementSystem.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "testDrives")
@AllArgsConstructor
@NoArgsConstructor

public class TestDrive {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "test_drive_id")
	private int id;
	
	private int carId;
	private int customerId;
	//private DateTime testDate;
	private boolean isConfirmByAdmin;
}
