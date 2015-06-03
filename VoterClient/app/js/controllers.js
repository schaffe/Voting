'use strict';

/* Controllers */
var phonecatApp = angular.module('phonecatApp', ['ngRoute'])
var endpoint = "http://10.0.1.40:8080/InteractivePollWFB/rest/";

phonecatApp.config(['$routeProvider', '$locationProvider', function($routeProvide, $locationProvider){

    $routeProvide
        .when('/region',{
            templateUrl:'template/regions.html',
            controller:'RegionsCtrl'
        })
        .when('/region/:regionId',{
            templateUrl:'template/candidates.html',
            controller:'CandidatesCtrl'
        })
        .when('/stats/region/:regionId',{
            templateUrl:'template/statistics.html',
            controller:'StatisticsCtrl'
        })
        //.when('/candidates',{
        //    templateUrl:'template/candidates.html',
        //    controller:'CandidatesCtrl'
        //})
        .when('/candidates/:candidateId', {
            templateUrl:'template/candidate-detail.html',
            controller:'CandidateDetailCtrl'
        })
        .otherwise({
            redirectTo: '/region'
        });
}]);

phonecatApp.controller('RegionsCtrl', function ($scope, $http) {
    $http.get(endpoint + 'region/').success(function(data) {
        $scope.regions = data;
    });
});

phonecatApp.controller('CandidatesCtrl',['$scope','$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {
    $scope.regionId = $routeParams.regionId;

    $scope.url = endpoint + 'region/' + $scope.regionId + '/candidate/';

    $http.get($scope.url).success(function(data) {
        $scope.candidates = data;
    });

    $scope.vote = function(id) {
        console.log(id);
        var voteUrl = $scope.url + id + '/vote/';
        $http.post(voteUrl,{});
    }
}]);

phonecatApp.controller('StatisticsCtrl',['$scope','$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {
    $scope.regionId = $routeParams.regionId;

    $scope.url = endpoint + 'region/' + $scope.regionId + '/stats/';

    $http.get($scope.url).success(function(data) {
        console.log(data);
        $scope.stats = data;
    });
}]);