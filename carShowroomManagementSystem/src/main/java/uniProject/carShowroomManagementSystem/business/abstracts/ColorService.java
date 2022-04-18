package uniProject.carShowroomManagementSystem.business.abstracts;

import java.util.List;

import uniProject.carShowroomManagementSystem.core.business.AddService;
import uniProject.carShowroomManagementSystem.core.business.BaseService;
import uniProject.carShowroomManagementSystem.core.utility.result.DataResult;
import uniProject.carShowroomManagementSystem.entity.concrete.Color;
import uniProject.carShowroomManagementSystem.entity.dto.ColorDto;

public interface ColorService extends BaseService<Color>, AddService<ColorDto>{
	DataResult<List<Color>> findByName(String name);
}
