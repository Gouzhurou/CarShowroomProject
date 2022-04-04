package uniProject.carShowroomManagementSystem.business.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uniProject.carShowroomManagementSystem.business.abstracts.CarService;
import uniProject.carShowroomManagementSystem.business.abstracts.CustomerService;
import uniProject.carShowroomManagementSystem.business.abstracts.SaleService;
import uniProject.carShowroomManagementSystem.business.constants.Messages;
import uniProject.carShowroomManagementSystem.core.utilities.results.DataResult;
import uniProject.carShowroomManagementSystem.core.utilities.results.ErrorDataResult;
import uniProject.carShowroomManagementSystem.core.utilities.results.ErrorResult;
import uniProject.carShowroomManagementSystem.core.utilities.results.Result;
import uniProject.carShowroomManagementSystem.core.utilities.results.SuccessDataResult;
import uniProject.carShowroomManagementSystem.core.utilities.results.SuccessResult;
import uniProject.carShowroomManagementSystem.dataAccess.abstracts.SaleDao;
import uniProject.carShowroomManagementSystem.entities.concretes.Car;
import uniProject.carShowroomManagementSystem.entities.concretes.Customer;
import uniProject.carShowroomManagementSystem.entities.concretes.Sale;
import uniProject.carShowroomManagementSystem.entities.dtos.SaleDto;

@Service
public class SaleManager implements SaleService{
	
	private SaleDao saleDao;
	private CarService carService;
	private CustomerService customerService;
	
	@Autowired
	public SaleManager(SaleDao saleDao, CarService carService, CustomerService customerService) {
		super();
		this.saleDao = saleDao;
		this.carService = carService;
		this.customerService = customerService;
	}

	@Override
	public Result add(SaleDto entity) {
		Sale sale = new Sale();
		sale.setConfirm(false);//kullanici alma islemini yapti, fakat daha yonetcicnin onyalamsı gerek
		sale.setSaleDate(LocalDate.now());
		
		Car car = carService.getById(entity.getCarId()).getData(); 
		//int saleCount = car.getSaleCount();
		//car.setSaleCount(saleCount);
		carService.setSaleCount(entity.getCarId());
		sale.setCar(car);
		
		Customer customer = customerService.getById(entity.getCustomerId()).getData();
		sale.setCustomer(customer);
		
		saleDao.save(sale);
		return new SuccessResult(Messages.added);
	}

	@Override
	public Result delete(int id) {
		Sale sale = getById(id).getData();
		if(sale == null) {
			return new ErrorResult(Messages.isNotExist);
		}
		saleDao.deleteById(id);
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public Result update(Sale entity) {
		saleDao.save(entity);
		return new SuccessResult(Messages.updated);
	}

	@Override
	public DataResult<Sale> getById(int id) {
		Sale sale = saleDao.findById(id).orElse(null);
		if(sale == null) {
			return new ErrorDataResult<Sale>(null, Messages.isNotExist);
		}
		return new SuccessDataResult<Sale>(sale, Messages.viewed);
	}

	@Override
	public DataResult<List<Sale>> getAll() {
		return new SuccessDataResult<List<Sale>>(saleDao.findAll(), Messages.listed);
	}

	@Override
	public DataResult<List<Sale>> findByIsConfirmTrue() {
		return new SuccessDataResult<List<Sale>>(saleDao.findByIsConfirmTrue(), Messages.listed);
	}

	@Override
	public DataResult<List<Sale>> findByIsConfirmFalse() {
		return new SuccessDataResult<List<Sale>>(saleDao.findByIsConfirmFalse(), Messages.listed);
	}

	@Override
	public DataResult<List<Sale>> findBySaleDate(LocalDate saleDate) {
		return new SuccessDataResult<List<Sale>>(saleDao.findBySaleDate(saleDate), Messages.listed);
	}

	@Override
	public DataResult<List<Sale>> findAllByCustomer_Id(int customerId) {
		return new SuccessDataResult<List<Sale>>(saleDao.findAllByCustomer_Id(customerId), Messages.listed);
	}

	@Override
	public DataResult<List<Sale>> findByCar_Id(int carId) {
		return new SuccessDataResult<List<Sale>>(saleDao.findByCar_Id(carId), Messages.listed);
	}

	@Override
	public Result confirmSale(int saleId) {
		Sale sale = getById(saleId).getData();
		if(sale == null) {
			return new ErrorResult(Messages.isNotExist);
		}
		if(sale.isConfirm()) {
			return new ErrorResult(Messages.alreadyConfirmed);
		}
		sale.setConfirm(true);
		
		saleDao.save(sale);
		return new SuccessResult(Messages.confirmedSale);
	}

	//belirli bir tarih araligindaki en cok satilan arabalari(artan sirada) goster
	@Override
	public DataResult<List<Car>> findBySaleDateBetweenOrderBySaleCount(LocalDate firstSaleDate,
			LocalDate lastSaleDate) {
		List<Sale> sales = saleDao.findBySaleDateBetweenOrderByCar_SaleCount(firstSaleDate, lastSaleDate);
		List<Car> cars = new ArrayList<Car>();
		for(Sale s : sales) {
			cars.add(s.getCar());
		}
		return new SuccessDataResult<List<Car>>(cars, Messages.viewed);
	}




}
