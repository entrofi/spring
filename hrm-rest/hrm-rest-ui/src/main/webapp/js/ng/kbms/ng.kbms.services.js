
tamsAppServices.factory('CountryService', ['$http', 
    function($http){
		var BASE_URL = "http://localhost:8080/kbms-rest-facade/rest/countries/v1";
		
		var doGet = function (uriExtension){
			return $http({
				method: 'GET',
				url: BASE_URL+"/"+uriExtension
			});
		}
		
		var doPost = function(uriExtension){
			return $http({
				method: 'POST',
				url: BASE_URL+"/"+uriExtension
			});
		}
		var find = function(id){
			return doGet("item-"+id)
		}
		
		var findList = function(limit, offset){
			return $http({
				method: 'GET',
				url: BASE_URL + "/list",
				params: {'limit': limit, 'offset': offset}
			});
		}
		
		var doPost = function(){
			
		}
	}
    ]
);