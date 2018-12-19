package com.zagwork.risktype.service;


import java.util.List;

import com.zagwork.risktype.model.Client;

public interface ClientService {
	
	Client findById(Long id);

	Client findByName(String name);

	void save(Client client);

	void update(Client client);

	void delete(Long id);

	void deleteAll();
	
	List<Client> findAll();

	boolean isClientExist(Client user);
}