package com.zagwork.risktype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zagwork.risktype.enums.RiskType;
import com.zagwork.risktype.model.Client;
import com.zagwork.risktype.repositories.ClientRepository;



@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Client findById(Long id) {
		return clientRepository.findOne(id);
	}

	public Client findByName(String name) {
		return clientRepository.findByName(name);
	}

	public void save(Client client) {
		choosePercental(client);
		clientRepository.save(client);
	}

	private void choosePercental(Client client) {
		switch (client.getRisckType()) {
		case A:
			client.setPercentRisk(0);
			break;
		case B:
			client.setPercentRisk(10);
			break;
		case C:
			client.setPercentRisk(20);
			break;
		default:
			client.setPercentRisk(null);
			break;
		}
	}

	public void update(Client client){
		choosePercental(client);
		save(client);
	}

	public void delete(Long id){
		clientRepository.delete(id);
	}

	public void deleteAll(){
		clientRepository.deleteAll();
	}

	public List<Client> findAll(){
		List<Client> clients = clientRepository.findAll();
		
		//clients = clients.stream().filter(c -> ( c.getRisckType().equals(RiskType.A) ? c.set
		
		return clients;
	}

	public boolean isClientExist(Client client) {
		return findByName(client.getName()) != null;
	}

}
