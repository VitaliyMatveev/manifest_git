package ru.komiparma.manifest.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.komiparma.manifest.domain.Client;
import ru.komiparma.manifest.service.IClientService;

public class UtilitesServiceImpl {
	@Autowired
	IClientService clnSrv;
	
	
	public void getStreet(){
		List<String> listAddress = new ArrayList<String>();
		for(Client c:clnSrv.getAll()){
			listAddress.add(c.getAddress());
		}
		System.out.println(listAddress.size());
	}

}
