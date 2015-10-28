'use strict';

sourceViewer.controller('sourceHeaderController', [
		'$rootScope',
		'DataAccess',
		'$scope',
		'$base64',
		function($rootScope, DataAccess, $scope, $base64) {

			$scope.filename = '';

			$scope.$on('showFilename', function(event, data) {
				console.log('showFilename received:' + data);
				$scope.filename = data.name;
			});

		} ]);
