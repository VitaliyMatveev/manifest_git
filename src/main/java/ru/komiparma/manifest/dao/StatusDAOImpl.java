package ru.komiparma.manifest.dao;

import org.springframework.stereotype.Repository;

import ru.komiparma.manifest.domain.Status;

@Repository
public class StatusDAOImpl extends GenericDAOImpl<Status> implements IStatusDAO{

	public StatusDAOImpl() {
		super(Status.class);
	}
	
}
