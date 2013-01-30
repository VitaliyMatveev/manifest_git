package ru.komiparma.manifest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.komiparma.manifest.dao.IStatusDAO;
import ru.komiparma.manifest.domain.Status;

@Repository
public class StatusServiceImpl extends GenericServiceImpl<Status> implements IStatusService{

	@Autowired
	public StatusServiceImpl(IStatusDAO statusDAO){
		super(statusDAO);
	}
}
