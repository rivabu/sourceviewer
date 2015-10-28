'use strict';

sourceViewer.controller('sourceTreeController', ['$rootScope', '$routeParams', '$location', 'DataAccess', '$scope', function ($rootScope, $routeParams, $location, DataAccess, $scope) {

    $scope.tree = '';
    $scope.projectId = $routeParams.projectId;
    
    $scope.init = function () {
        $scope.projectId = $routeParams.projectId;
    	$scope.getTree($scope.projectId);
    }
    
    /*
     * It's worth noting that $broadcast is used to delegate events to child or sibling scopes, whereas $emit will bubble events upwards to the scope's parents, hence;

		When choosing $broadcast (and not $emit), one should either inject the root scope for tying the $on (as you nicely did) or call $on on the receiver's isolated scope, be it a child scope of the dispatcher.
     */
    $scope.showFile= function (id) {
    	$rootScope.$broadcast('showFile', id);
    	console.log('showFile send');
    	console.log("file clicked: " + id);
    };
    
    $scope.collapseAll = function() {
    	console.log('collapseAll()');
    	angular.forEach($scope.tree, function (elem) {
			elem.status = 'closed';
    	})
    	$scope.parsedTree = parseTreeToHtml();
    }
    
    $scope.expandAll = function() {
    	console.log('expandAll()');
    	angular.forEach($scope.tree, function (elem) {
			elem.status = 'open';
    	})
    	$scope.parsedTree = parseTreeToHtml();
    }

    $scope.openCloseDir= function (id) {
    	console.log("openCloseDir: " + id);
    	angular.forEach($scope.tree, function (elem) {
    		if (elem.id == id) {
    			if (elem.status == 'open') {
    				elem.status = 'closed';
    			} else {
    				elem.status = 'open';
    			}
    		}
    	})
    	$scope.parsedTree = parseTreeToHtml();
    };
    
     $scope.getTree = function(projectId) {
    	 DataAccess.getTree(projectId).then(function (result) {
    		 $scope.tree = result.elements;
	    	 angular.forEach($scope.tree, function (elem) {
				elem.status = 'open';
	    	 })
    		 var parsedTree = parseTreeToHtml();
    		 $scope.parsedTree = parsedTree;
    	 }, function (result) {
            console.log('error' + result);
         });
    };	
     
    function parseTreeToHtml() {
    	var elements = $scope.tree;
     	var endresult = '<ul>';
     	for (var j in elements) {
            var type = elements[j].type;
			if (type === 'root') {
				endresult = endresult + '<li id=\'' + elements[j].id + '\' class=\'open\'><div class=\'dir open-close\'></div> <span class=\'dir open-close project\'>' + elements[j].name +'</span>'
		        endresult = endresult + '<ul>';
			}
			if (type === 'endroot') {
				endresult = endresult + '</ul>';
			}
			if (type === 'dir') {
				endresult = endresult + '<li id=\'' + elements[j].id + '\' class=\'' + elements[j].status + '\' ><div class=\'dir open-close\' ng-click=\"openCloseDir(\'' + elements[j].id + '\')\"></div> <span class=\'dir open-close folder\' ng-click=\"openCloseDir(\'' + elements[j].id + '\')\">' + elements[j].name +'</span>';
		        endresult = endresult + '<ul>';
			}
			if (type === 'enddir') {
				endresult = endresult + '</ul>';
			}
			if (type === 'node') {
				endresult = endresult + '<li id=\'' + elements[j].id + '\'><a href class=\'file ' + elements[j].extension + '\' ng-click=\"showFile(\'' + elements[j].fileId + '\')\">' + elements[j].name +'</a></li>';
			}
     	}
     	endresult = endresult + '</ul>';
     	endresult = endresult.replace(/\t/g, '');    	
     	return endresult;
    } 
    
    $scope.init();
    
}]);

//Here $watch used for automatic rendering, it listening directive's content value if content changed then it works. 
//If you think your directive works only single time then you don't need $watch. 
//Here $parse used for parsing the string content and $compile will link newly generated HTML with scope.

sourceViewer.directive('tree', function($compile, $parse) {
    return {
      restrict: 'E',
      link: function(scope, element, attr) {
        scope.$watch(attr.content, function() {
          element.html($parse(attr.content)(scope));
          $compile(element.contents())(scope);
        }, false);
      }
    }
  });

