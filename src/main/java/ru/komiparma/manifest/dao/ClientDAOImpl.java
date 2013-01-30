package ru.komiparma.manifest.dao;

import org.springframework.stereotype.Repository;

import ru.komiparma.manifest.domain.Client;

@Repository
public class ClientDAOImpl extends GenericDAOImpl<Client> implements IClientDAO{
	
	public ClientDAOImpl(){
		super(Client.class);
	}
}