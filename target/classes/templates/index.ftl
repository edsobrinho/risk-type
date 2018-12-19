<!DOCTYPE html>

<html lang="pt-br" ng-app="crudApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/bootstrap-3.3.7/dist/css/bootstrap-theme.css" rel="stylesheet"/>
        <link href="css/font-awesome/css/font-awesome.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>

        <div ui-view></div>
        <script src="js/lib/jquery/dist/jquery.min.js" ></script>
        <script src="css/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/ClientService.js"></script>
        <script src="js/app/ClientController.js"></script>
    </body>
</html>