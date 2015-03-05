hrmControllers.controller('EmployeeController', ['$scope', '$http','$modal', function ($scope, $http, $modal) {

	

	$scope.gridOptions = {
		pagingPageSizes: [25, 50, 75],
		pagingPageSize: 25,
		rowsPerPage:200,
		columnDefs: [
			{name:'Action', width:60, cellTemplate: '<div class="grid-button-group"><button id="deleteBtn" type="button" class="btn btn-danger btn-xs" ng-click="getExternalScopes().remove(row)"><i class="fa fa-trash-o"></i>'+
										   			'</button> <button id="editBtn" type="button" class="btn btn-success btn-xs" ng-click="getExternalScopes().edit(row.entity)" ><i class="fa fa-pencil"></i></button></div>'},
		 	{name:'id'},
		    {name:'name'},
		    {name:'code'},
		    {name: 'region.name'}
		]
	};
	
	$scope.entity = {};
	
	$scope.addNew = function(entity){
		$scope.entity = (entity)? entity:{};
		var modalInstance = $modal.open({
			templateUrl: 'editModal.html',
			controller: 'EmployeeModalController',
			size: "lg",
			resolve: {
				entity: function(){
					return $scope.entity;
				},
				regions: function(){
					return $scope.regions;
				},
				gridData: function(){
					return $scope.gridOptions.data;
				}
			}
		});

		modalInstance.result.then(
			function () {}, 
			function () {}
		);
	};
	
	$scope.removeRow = function(){
       
    
	};
	
	
	
	$scope.modalscope = {
	  edit: function(entity) {
		console.log(entity);
		$scope.entity = entity;
	  	var modalInstance = $modal.open({
			templateUrl: 'editModal.html',
			controller: 'EmployeeModalController',
			size: "lg",
			resolve: {
				entity: function(){
					return $scope.entity;
				},
				regions: function(){
					return $scope.regions;
				},      
				gridData: function(){
					return $scope.gridOptions.data;
				}
			}
		});

		modalInstance.result.then(
			function () {}, 
			function () {}
		);
	  },
	  remove: function(row){
		  console.log("removing item:");
		  var gridInst = row.grid;
		  $http.delete('http://localhost:8080/kbms-rest-facade/rest/countries/v1/item-'+row.entity.id).success(function(status){
		  	console.log("Delete request status: " + status);
		  	$scope.list();
		  });
	  }
	};
}]);

hrmControllers.controller('EmployeeModalController', function ($scope, $modalInstance, $http, entity, regions, gridData) {

	  $scope.entity = entity;
	  $scope.regions = regions;
	  console.log('items passed to controller' + entity);
	 
	  $scope.save = function () {
	  	if(this.editForm.$valid){
	  		$http.post('http://localhost:8080/kbms-rest-facade/rest/countries/v1', $scope.entity).success(function (data, status) {
	  			console.log("response status" + status);
	  			gridData.push(data);
		  		$modalInstance.close();
	  		}).
	  		error(function(data, status){
	  			console.log("Error saving data: " + data + " Status: " + status);
	  			$scope.validationErrors = data;
	  		});

	  	}else{
	  		console.log("Error: " + this.editForm.$error);
	  	}
	  	console.log("-----Entity---- \n Code:" + entity.code + " Name:" + entity.name+ " Region:" + entity.region);
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
	});