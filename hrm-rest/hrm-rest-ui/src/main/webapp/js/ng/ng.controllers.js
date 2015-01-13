var tamsControllers = angular.module('app.controllers', [])
	.factory('settings', ['$rootScope', function($rootScope){
		// supported languages
		
		var settings = {
			languages: [
				{
					language: 'English',
					translation: 'English',
					langCode: 'en',
					flagCode: 'us'
				},
				{
					language: 'Espanish',
					translation: 'Espanish',
					langCode: 'es',
					flagCode: 'es'
				},
				{
					language: 'German',
					translation: 'Deutsch',
					langCode: 'de',
					flagCode: 'de'
				},
				{
					language: 'Korean',
					translation: '한국의',
					langCode: 'ko',
					flagCode: 'kr'
				},
				{
					language: 'French',
					translation: 'français',
					langCode: 'fr',
					flagCode: 'fr'
				},
				{
					language: 'Portuguese',
					translation: 'português',
					langCode: 'pt',
					flagCode: 'br'
				},
				{
					language: 'Russian',
					translation: 'русский',
					langCode: 'ru',
					flagCode: 'ru'
				},
				{
					language: 'Chinese',
					translation: '中國的',
					langCode: 'zh',
					flagCode: 'cn'
				}
			],
			
		};

		return settings;
		
	}])

	.controller('PageViewController', ['$scope', '$route', '$animate', function($scope, $route, $animate) {
		// controler of the dynamically loaded views, for DEMO purposes only.
		/*$scope.$on('$viewContentLoaded', function() {
			
		});*/
	}])

	.controller('TamsAppController', ['$scope', function($scope) {

		$scope.toggle = function() {
        	$scope.$broadcast('event:toggle');
    	}
	}])

	.controller('gridController', ['$scope', '$http','$modal', function ($scope, $http, $modal) {

		$scope.gridOptions = {
			pagingPageSizes: [25, 50, 75],
			pagingPageSize: 25,
			rowsPerPage:200,
			columnDefs: [
				{name:'Action', width:60, cellTemplate: '<div class="grid-button-group"><button id="deleteBtn" type="button" class="btn btn-danger btn-xs" ng-click=""><i class="fa fa-trash-o"></i>'+
											   			'</button> <button id="editBtn" type="button" class="btn btn-success btn-xs" ng-click="getExternalScopes().edit(row.entity.id)" ><i class="fa fa-pencil"></i></button></div>'},
			 	{name:'id'},
			    {name:'name'},
			    {name:'age'},
			    {name:'address.city'},
			    {name:'age again', field:'age'}
			]
		};

		$http.get('http://localhost:8080/kbms-ui/ajax/sample-data.json').success(function (data) {
			$scope.gridOptions.data = data;
		});

		$scope.addNew = function(){
			var modalInstance = $modal.open({
				templateUrl: 'editModal.html',
				controller: 'ModalInstanceController',
				size: "lg",
				resolve: {}
			});

			modalInstance.result.then(
				function () {}, 
				function () {}
			);
		}

		$scope.modalscope = {
		  edit: function() {
		  	var modalInstance = $modal.open({
				templateUrl: 'editModal.html',
				controller: 'ModalInstanceController',
				size: "lg",
				resolve: {}
			});

			modalInstance.result.then(
				function () {}, 
				function () {}
			);
		  },
		};
	}])
	
	// modal instance for modal in grid
	.controller('ModalInstanceController', ['$scope', '$modalInstance', function ($scope, $modalInstance, data) {
		$scope.ok = function () {
			$modalInstance.close(vars);
		};

		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};
	}])

	.controller('LangController', ['$scope', 'settings', 'localize', function($scope, settings, localize) {
		$scope.languages = settings.languages;
		$scope.currentLang = settings.currentLang;
		$scope.setLang = function(lang) {
			settings.currentLang = lang;
			$scope.currentLang = lang;
			localize.setLang(lang);
		}

		// set the default language
		$scope.setLang($scope.currentLang);

	}])

	.controller('ActivityDemoCtrl', ['$scope', function($scope) {
		var ctrl = this;
		ctrl.getDate = function() {
			return new Date().toUTCString();
		};

		$scope.refreshCallback = function(contentScope, done) {

			// use contentScope to get access with activityContent directive's Control Scope
			console.log(contentScope);

			// for example getting your very long data ...........
			setTimeout(function() {
				done();
			}, 3000);

			$scope.footerContent = ctrl.getDate();
		};

		$scope.items = [
			{
				title: 'Msgs',
				count: 14, 
				src: 'ajax/notify/mail.html',
				onload: function(item) {
					console.log(item);
					alert('[Callback] Loading Messages ...');
				}
			},
			{
				title: 'Notify',
				count: 3,
				src: 'ajax/notify/notifications.html'
			},
			{
				title: 'Tasks',
				count: 4,
				src: 'ajax/notify/tasks.html',
				//active: true
			}
		];

		$scope.total = 0;
		angular.forEach($scope.items, function(item) {
			$scope.total += item.count;
		})

		$scope.footerContent = ctrl.getDate();
		
	}])