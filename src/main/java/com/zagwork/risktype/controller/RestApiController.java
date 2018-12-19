package com.zagwork.risktype.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zagwork.risktype.model.Client;
import com.zagwork.risktype.service.ClientService;
import com.zagwork.risktype.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ClientService clientService; 

	@RequestMapping(value = "/client/", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> listAllClients() {
		List<Client> clients = clientService.findAll();
		if (clients.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}

	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getClient(@PathVariable("id") long id) {
		logger.info("Buscando cliente com id {}", id);
		Client client = clientService.findById(id);
		if (client == null) {
			logger.error("Cliente com o id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Cliente com o id " + id + " não encontrado"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@RequestMapping(value = "/client/", method = RequestMethod.POST)
	public ResponseEntity<?> createClient(@RequestBody Client client, UriComponentsBuilder ucBuilder) {
		logger.info("Criando cliente : {}", client);

		if (clientService.isClientExist(client)) {
			logger.error("Não é possível criar. Um cliente com nome {} já existe", client.getName());
			return new ResponseEntity(new CustomErrorType("Não é possível criar. Um cliente com nome " + 
			client.getName() + " já existe."),HttpStatus.CONFLICT);
		}
		clientService.save(client);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/client/{id}").buildAndExpand(client.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateClient(@PathVariable("id") long id, @RequestBody Client client) {
		logger.info("Atualizando cliente com id {}", id);

		Client currentClient = clientService.findById(id);

		if (currentClient == null) {
			logger.error("Não é possível atualizar. Cliente com id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Não é possível atualizar. Cliente com id " + id + " não encontrado."), HttpStatus.NOT_FOUND);
		}

		currentClient.setName(client.getName());
		currentClient.setCreditLimit(client.getCreditLimit());
		currentClient.setRisckType(client.getRisckType());

		clientService.update(currentClient);
		return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
	}

	@RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteClient(@PathVariable("id") long id) {
		logger.info("Buscando e excluindo cliente com id {}", id);

		Client client = clientService.findById(id);
		if (client == null) {
			logger.error("Não é possível excluir. Cliente com id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Não é possível excluir. Cliente com id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}
		clientService.delete(id);
		return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/client/", method = RequestMethod.DELETE)
	public ResponseEntity<Client> deleteAllClients() {
		logger.info("Excluindo todos os clientes");

		clientService.deleteAll();
		return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}

}