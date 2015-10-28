sourceViewer.controller('frontPageController', ['$rootScope', '$location', 'DataAccess', '$scope', function ($rootScope, $location, DataAccess, $scope) {

    $scope.init = function() {
    	console.log('bla');
    	DataAccess.getProjects().then(function(projecten) {
	       console.log('projecten listed: ' + JSON.stringify(projecten));
	       $scope.projecten = projecten.projecten;
	   	   console.log('result: ' + projecten);
   	 	}, function (result) {
           console.log('error' + result);
        });
    }
    
    $scope.showProject = function(id) {
    	console.log('project selected with id: ' + id);
    	var url = '/main/' + id;
		$location.path(url);
		console.log('location path: ' + url);
    }
    
    $scope.delete = function(id) {
    	DataAccess.deleteProject(id);
    	$location.path('/');
    }
    $scope.init();
}]);


