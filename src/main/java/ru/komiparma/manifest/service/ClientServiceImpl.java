package ru.komiparma.manifest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.komiparma.manifest.dao.IClientDAO;
import ru.komiparma.manifest.domain.Client;

@Repository
public class ClientServiceImpl extends GenericServiceImpl<Client> implements IClientService{
	
	@Autowired
	public ClientServiceImpl(IClientDAO clientDAO){
		super(clientDAO);
	}
}
