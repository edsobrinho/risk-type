var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/risktype/',
    USER_SERVICE_API : 'http://localhost:8080/risktype/api/client/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'ClientController',
                controllerAs:'ctrl',
                resolve: {
                    clients: function ($q, ClientService) {
                        console.log('Carregue todos os clientes');
                        var deferred = $q.defer();
                        ClientService.loadAllClients().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

