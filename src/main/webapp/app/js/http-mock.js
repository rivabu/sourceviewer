'use strict';
  
var myAppDev = angular.module('myAppDev', ['sourceViewer', 'ngMockE2E']);
myAppDev.run(function($httpBackend) {
	  
	  var tree =  [    { id : 1212, type: 'root', name: 'Rients Test' }, 
				{ id : 37980, type: 'dir', name: 'notes', status: 'open' },
				{ id : 37981, type: 'node', ext: 'js', name: 'data.js' },
				{ id : 37981, type: 'node', ext: 'txt', name: 'file.txt' },
				{ id : 37981, type: 'node', ext: 'rd', name: 'readme.rd' },
				{ id : 37982, type: 'node', ext: 'png', name: 'sports-snippet.png' },
				{ id : 37983, type: 'node', ext: 'png', name: 'sports-v1.0.png' },
				{ type: 'enddir' },
 					{ id : 37984, type: 'node', ext: 'xml', name: 'pom.xml' },
 					{ id : 37984, type: 'node', ext: 'rd', name: 'readme.rd' },
				{ type: 'endroot' }
			];
	  var HTTP_STATUS_OK = 200;
	  var responseHeaders = { "content-type": "application/json" }
  $httpBackend.whenGET("/api/sourcetree").respond(HTTP_STATUS_OK, tree, responseHeaders); 
  $httpBackend.whenGET(/partials/).passThrough();
//  $httpBackend
//  	.whenGET(/^\/app\//)
//  	.passThrough();   

});


//var pManageCreditcardsWADev = angular.module('pManageCreditcardsWADev', ['pManageCreditcardsWAApp', 'ngMockE2E' ]);
//var s = null;
//pManageCreditcardsWADev.run(function ($httpBackend) {
//    var responseHeaders = { "content-type": "application/json" }, HTTP_STATUS_OK = 200;
//    $httpBackend.whenGET("/app/p-manage-creditcards/retrieve/cards?").respond(HTTP_STATUS_OK, creditcardsJustOne, responseHeaders);
//    $httpBackend.whenPOST("/app/p-manage-creditcards/verify/cards").respond(HTTP_STATUS_OK, verificationOK, responseHeaders);
//    $httpBackend.whenPOST("/app/p-manage-creditcards/determineclose/cards").respond(HTTP_STATUS_OK, determineClosePositiveBalance, responseHeaders);
//    $httpBackend.whenPOST("/app/p-manage-creditcards/close/cards").respond(HTTP_STATUS_OK, closeOK, responseHeaders);
//    $httpBackend.whenGET("/particulier/ik-en-ing/api/email").respond(HTTP_STATUS_OK, emailFromService, responseHeaders);
//    $httpBackend.whenGET(/partials/).passThrough();
//});