package com.rmit.sept.majorProject.dto;

public class WorkerServiceBlueprint {

	private Long workerID;
	private Long[] serviceID;

	public WorkerServiceBlueprint(Long workerID, Long[] serviceID) {
		this.workerID = workerID;
		this.serviceID = serviceID;
	}

	public Long getWorkerID() {
		return this.workerID;
	}

	public void setWorkerID(Long ID) {
		this.workerID = ID;
	}

	public Long[] getServiceID() {
		return this.serviceID;
	}

	public void setServiceID(Long[] serviceID) {
		this.serviceID = serviceID;
	}

	public void addServiceID(Long serviceID) {
		Long[] newServiceIDArray = new Long[this.serviceID.length + 1];
		int i = 0;
		for (Long ID : this.serviceID) {
			newServiceIDArray[i++] = ID;
		}
		newServiceIDArray[i] = serviceID;
		this.serviceID = newServiceIDArray;
	}

	public Long serviceIDCheck() {
		for (Long ID : this.serviceID) {
			if (ID < 1) {
				return ID;
			}
		}
		return null;
	}

}
