package uniProject.carShowroomManagementSystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uniProject.carShowroomManagementSystem.business.abstracts.RoleService;
import uniProject.carShowroomManagementSystem.business.constants.Messages;
import uniProject.carShowroomManagementSystem.core.entities.Role;
import uniProject.carShowroomManagementSystem.core.utilities.results.DataResult;
import uniProject.carShowroomManagementSystem.core.utilities.results.ErrorDataResult;
import uniProject.carShowroomManagementSystem.core.utilities.results.ErrorResult;
import uniProject.carShowroomManagementSystem.core.utilities.results.Result;
import uniProject.carShowroomManagementSystem.core.utilities.results.SuccessDataResult;
import uniProject.carShowroomManagementSystem.core.utilities.results.SuccessResult;
import uniProject.carShowroomManagementSystem.dataAccess.abstracts.RoleDao;
import uniProject.carShowroomManagementSystem.entities.dtos.RoleDto;

@Service
public class RoleManager implements RoleService {
	
	private RoleDao roleDao;
	
	@Autowired
	public RoleManager(RoleDao roleDao) {
		super();
		this.roleDao = roleDao;
	}

	@Override
	public Result add(RoleDto entity) {
		Role role = new Role();
		role.setName(entity.getName());
		roleDao.save(role);
		return new SuccessResult(Messages.added);
	}
	
	@Override
	public Result delete(int id) {
		Role role = getById(id).getData();
		if(role == null) {
			return new ErrorResult(Messages.isNotExist);
		}
		roleDao.deleteById(id);
		return new SuccessResult(Messages.deleted);
	}

	@Override
	public Result update(Role entity) {
		roleDao.save(entity);
		return new SuccessResult(Messages.added);
	}

	@Override
	public DataResult<Role> getById(int id) {
		Role role = roleDao.findById(id).orElse(null);
		if(role == null) {
			return new ErrorDataResult<Role>(null, Messages.isNotExist);
		}
		return new SuccessDataResult<Role>(role, Messages.viewed);
	}

	@Override
	public DataResult<List<Role>> getAll() {
		return new SuccessDataResult<List<Role>>(roleDao.findAll(), Messages.listed);
	}



}