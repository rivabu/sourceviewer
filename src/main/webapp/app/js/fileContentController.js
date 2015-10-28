'use strict';

sourceViewer.controller('fileContentController', [ '$rootScope', 'DataAccess', '$scope', '$base64',
		function($rootScope, DataAccess, $scope, $base64) {

			$scope.found = false;
			$scope.content = '';
			$scope.binary = false;

			$scope.getFileContent = function() {
				return $scope.content;
			}

			$scope.$on('showFile', function(event, data) {
				console.log('showFile received:' + data);
				$scope.found = true;
				DataAccess.getFile(data).then(function(result) {
					$rootScope.$broadcast('showFilename', result);

					$scope.binary = result.binary;
					console.log(result.encodedContent);
					if (result.binary) {
						console.log('binary file found');
						$scope.content = result.encodedContent;
					} else {
						var decodedString = $base64.decode(result.encodedContent);
						$scope.content = decodedString;
						console.log($scope.content);
					}
				}, function(result) {
					console.log('error' + result);
				});
				return $scope.content;

			});

		} ]);
