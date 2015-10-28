'use strict';

var sourceViewer = angular.module('sourceViewer', ['ngResource', 'ngRoute', 'ngSanitize', 'base64', 'hljs', 'mc.resizer']);

sourceViewer.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/main/:projectId', {templateUrl: "app/partials/mainpage.html"})
        .when('/frontpage', {templateUrl: "app/partials/frontpage.html", controller: 'frontPageController'})
        .otherwise({redirectTo: '/frontpage'});
}]);