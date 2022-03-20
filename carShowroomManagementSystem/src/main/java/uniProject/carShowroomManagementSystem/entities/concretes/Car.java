package uniProject.carShowroomManagementSystem.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor

public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int id;
	
	private int brandId;
	private int colorId;
	
	@Column(name = "name")
	private String name;
	//model yili
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "description")
	private String description;
}
