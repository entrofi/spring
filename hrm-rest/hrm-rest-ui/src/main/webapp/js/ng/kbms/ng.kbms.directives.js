'use strict';

/* Directives */

hrmControllers.directive('hrmFieldValidator', ['$timeout', function($timeout){


	/**
	 * Directive controller
	 *
	 */
	 var ValidatorController = function($scope){

	 	$scope.showMessage = false;
	 	$scope.debug = true;

	 	this.checkMessage = function(hasError){
	 		$scope.$apply( function() {
	 			$scope.showMessage = hasError;
	 		});
	 	};// end checkMessage
	 };



	 var linkFunc = function(scope, element, attrs, formController){
	 	var blurred = false;
	 	//find the transcluded form element
	 	var inputElement = element[0].querySelector('[name]');
	 	//Convert to ng element
	 	var inputNgElement = angular.element(inputElement);

	 	//Get the value of name attribute so that we can use it to check angular form states 
	 	var inputName = inputNgElement.attr('name');

	 	scope.ngInputCtrl= formController[inputName];
	 	scope.showMessage = false;
	 	var hasError = false;


	 	var toggleStatus = function(invalid){
        	if(!formController[inputName].$pristine){
        	element.toggleClass('has-error', invalid);
        	element.toggleClass('has-success', !invalid);
        	angular.element(element[0].querySelector('.glyphicon')).toggleClass('glyphicon-remove', invalid);
        	angular.element(element[0].querySelector('.glyphicon')).toggleClass('glyphicon-ok', !invalid);
        	scope.showMessage = invalid;
	        }else{
	        	element.removeClass('has-error');
        		element.removeClass('has-success');
        		angular.element(element[0].querySelector('.glyphicon')).removeClass('glyphicon-remove', invalid);
        		angular.element(element[0].querySelector('.glyphicon')).removeClass('glyphicon-ok', !invalid);
	        }
        }

        /**
        * blur binding
        **/ 
	 	inputNgElement.bind('blur', function(evt){
	 		blurred = true;
	 		hasError = scope.ngInputCtrl.$invalid && scope.ngInputCtrl.$dirty;
	 		scope.validatorCtrl.checkMessage(hasError);
	 		toggleStatus(hasError);
	 		console.log('Blurred-->' + inputName);

	 	});

	 	scope.$watch(function () {
          return formController[inputName].$invalid && formController[inputName].$dirty;
        }, function (invalidityStatus, oldStatus) {        	
          console.log('new status=' + invalidityStatus + ' old status=' + oldStatus + " Blurred=" + blurred);
        	if(!blurred){
        		//do nothing baby
        		return;
        	}
          toggleStatus(invalidityStatus);
        });


        scope.$on('reset-validation', function(){
        	return $timeout(function(){
        		console.log('-->resetting input control...' + formController[inputName].$viewValue);
        		formController[inputName].$pristine = true;
        		formController[inputName].$dirty = false;
        		formController[inputName].$viewValue = ''; //This is not working why???? 
        		toggleStatus(false);
        		scope.validatorCtrl.checkMessage(false);
        		return blurred = false;
        	}, 1000, false);
        });


	 };//end linkFunc



	 return {
		restrict: 'E',
		require: '^form',
		transclude: true,
		controllerAs: 'validatorCtrl',
		controller: ValidatorController,
		scope: {
			label: '@controlLabel',
			message: '@controlMsg',
			requiredMessage: '@requiredMsg',
		},
		compile: function(){ return linkFunc;},//We should make a compilation here checking for prerequisites
		templateUrl: 'js/ng/kbms//directive-templates/hrm-field-validator.html'
	};//end return


}]);