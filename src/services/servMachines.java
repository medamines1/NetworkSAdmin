package services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Mydaos.machinesInt;
import hibernateClasses.machines;


@Service
public class servMachines implements machinesInt {
	
	@Autowired
	private machinesInt mach;

	@Override
	public Long getLengthofmach(String type) {
		return this.mach.getLengthofmach(type);
	}

	@Override
	public Map<String, String> getAllMachines() {

		return mach.getAllMachines();
	}
	
	@Override
	public Long getLengthActive() {
		return mach.getLengthActive();
	}
	


}
