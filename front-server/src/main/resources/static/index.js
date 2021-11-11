(function ($localStorage) {
  'use strict';

  angular
  .module('app', ['ngRoute', 'ngStorage'])
  .config(config)
  .run(run);

  function config($routeProvider, $httpProvider) {
    $routeProvider
          .when('/', {
            templateUrl: 'home/home.html',
            controller: 'homeController'
          })
          .when('/products', {
          templateUrl: 'products/products.html',
          controller: 'productsController'
          })
          .when('/orders', {
            templateUrl: 'orders/orders.html',
            controller: 'ordersController'
          })
          .otherwise({
            redirectTo: '/'
          });
  }

  const contextPath = 'http://localhost:5555';

  function run($rootScope, $http, $localStorage) {
    if ($localStorage.currentUser) {
      $http.defaults.headers.common.Authorization = 'Bearer '
          + $localStorage.currentUser.token;
    }
  }
})();

angular.module('app').controller('indexController',
    function ($scope, $http, $localStorage, $location) {
      const contextPath = 'http://localhost:5555';

      $scope.tryToAuth = function () {

        $http.post(contextPath + '/api/v1/auth/login', $scope.user)
        .then(function successCallback(response) {
          if (response.data.token) {
            $http.defaults.headers.common.Authorization = 'Bearer '
                + response.data.token;
            $localStorage.currentUser = {
              login: $scope.user.login,
              token: response.data.token
            };

            $scope.currentUserName = $scope.user.login;

            $scope.user.login = null;
            $scope.user.password = null;
          }
        }, function errorCallback(response) {
          console.log(response.data.body)
        });
      };

      $scope.tryToLogout = function () {
        $scope.clearUser();

        $location.path('/');
        if ($scope.user.username) {
          $scope.user.username = null;
        }
        if ($scope.user.password) {
          $scope.user.password = null;
        }
      };

      $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
      };

      $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
          return true;
        } else {
          return false;
        }
      };
    });