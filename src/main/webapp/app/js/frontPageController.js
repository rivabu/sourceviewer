sourceViewer.controller('frontPageController', ['$rootScope', '$location', 'DataAccess', '$scope', function ($rootScope, $location, DataAccess, $scope) {

	var selectedId = -1;
	var editFlag = false;
	$scope.project = {};
	
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
    $scope.startEdit = function(id) {
    	console.log('start edit');
    	editFlag = true;
    	selectedId = id;
    }
    $scope.delete = function(id) {
    	DataAccess.deleteProject(id);
    	$location.path('/');
    }
    $scope.cancel = function() {
    	selectedId = -1;
    	editFlag = false;
    }
    
    $scope.save = function() {
    	angular.forEach($scope.projecten, function (project) {
    		if (project.id == selectedId) {
    	    	console.log('saved: ' + JSON.stringify(project));
    	    	DataAccess.updateProject(project);
    		}
    	})
    	selectedId = -1;
    	editFlag = false;
    }

    $scope.isInReadMode = function(id) {
    	if (id == selectedId) {
    		return false;
    	}
    	return true;
    }
    
    $scope.isInEditMode = function(id) {
    	if (id == selectedId && editFlag) {
    		return true;
    	}
    	return false;
    }

    $scope.init();
}]);


