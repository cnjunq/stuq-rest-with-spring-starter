angular.module('welcome', [ 'ngRoute' ]).config(function($routeProvider) {

    $routeProvider.when('/', {
        templateUrl : 'home.html',
        controller : 'home'
    }).otherwise('/');

}).controller('home', function($scope, $http, $window) {
    var headers = {
        "Accept" : "application/json", 
    };
    
    $http.get("http://localhost:8082/api/roles/7", {
        headers : headers
    }).success(function(data) {
        $scope.role = data;
    })
});