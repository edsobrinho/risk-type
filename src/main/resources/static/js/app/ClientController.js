'use strict';

angular.module('crudApp').controller('ClientController',
    ['ClientService', '$scope',  function( ClientService, $scope) {

        var self = this;
        self.client = {};
        self.clients=[];

        self.submit = submit;
        self.getAllClients = getAllClients;
        self.createClient = createClient;
        self.updateClient = updateClient;
        self.removeClient = removeClient;
        self.editClient = editClient;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submetendo');
            if (self.client.id === undefined || self.client.id === null) {
                console.log('Salvando novo cliente', self.client);
                createClient(self.client);
            } else {
                updateClient(self.client, self.client.id);
                console.log('Cliente atualizado com id ', self.client.id);
            }
        }

        function createClient(client) {
            console.log('Criando um cliente');
            ClientService.createClient(client)
                .then(
                    function (response) {
                        console.log('Cliente salvo com sucesso');
                        self.successMessage = 'Cliente salvo com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        self.client={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Erro ao salvar o cliente');
                        self.errorMessage = 'Erro ao salvar o cliente: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateClient(client, id){
            console.log('Atualizando o cliente');
            ClientService.updateClient(client, id)
                .then(
                    function (response){
                        console.log('Cliente atualizado com sucesso');
                        self.successMessage='Cliente atualizado com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Erro ao atualizar o cliente');
                        self.errorMessage='Erro ao atualizar o cliente '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeClient(id){
            console.log('Excuindo o cliente com id '+id);
            ClientService.removeClient(id)
                .then(
                    function(){
                        console.log('Cliente '+id + 'removido com sucesso');
                    },
                    function(errResponse){
                        console.error('Erro ao remover o cliente '+id +', Erro :'+errResponse.data);
                    }
                );
        }


        function getAllClients(){
            return ClientService.getAllClients();
        }

        function editClient(id) {
            self.successMessage='';
            self.errorMessage='';
            ClientService.getClient(id).then(
                function (client) {
                    self.client = client;
                },
                function (errResponse) {
                    console.error('Erro ao atualizar o cliente ' + id + ', Erro :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.client={};
            $scope.myForm.$setPristine();
        }
    }


    ]);