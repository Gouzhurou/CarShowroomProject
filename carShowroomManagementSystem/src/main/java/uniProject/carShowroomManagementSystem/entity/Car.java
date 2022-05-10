package uniProject.carShowroomManagementSystem.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializier", "handler", "sales"})

public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color carColor;
	
	@Column(name = "name", nullable = false)
	//@Size(min = 3, max = 10)
	private String name;
	
	//modelYear
	@JsonFormat(pattern = "yyyy")
	private LocalDate modelYear;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@JsonIgnoreProperties
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Sale> sales;
	
	@Column(name = "sale_count")
	private int saleCount;
	
	/*@JsonIgnore
	@OneToMany(mappedBy = "car")
	private List<TestDrive> testDrives;*/
}