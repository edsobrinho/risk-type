'use strict';

angular.module('crudApp').factory('ClientService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllClients: loadAllClients,
                getAllClients: getAllClients,
                getClient: getClient,
                createClient: createClient,
                updateClient: updateClient,
                removeClient: removeClient
            };

            return factory;

            function loadAllClients() {
                console.log('Buscando todos os clientes');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Buscado com sucesso todos os clientes');
                            $localStorage.clients = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Erro ao carregar clientes');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllClients(){
                return $localStorage.clients;
            }

            function getClient(id) {
                console.log('Buscando cliente com id :'+id);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Obtido com sucesso cliente com id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro ao carregar o cliente com o id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createClient(client) {
                console.log('Criando cliente');
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API, client)
                    .then(
                        function (response) {
                            loadAllClients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Erro ao criar o cliente : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateClient(client, id) {
                console.log('Atualizando cliente com id '+id);
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + id, client)
                    .then(
                        function (response) {
                            loadAllClients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro ao atualizar o cliente com o id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeClient(id) {
                console.log('Removendo o cliente com o id '+id);
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllClients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro ao remover o cliente com o id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);