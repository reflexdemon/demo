angular.module('ng-gulp.customer', ['ui.grid', 'ngDialog', 'ui.grid.resizeColumns']).config(['ngDialogProvider', '$routeProvider', function (ngDialogProvider, $routeProvider) {
    'use strict';
    ngDialogProvider.setDefaults({
        className: 'panel panel-info',
        plain: true,
        showClose: true,
        closeByDocument: true,
        closeByEscape: true
    });
    //Customer
    $routeProvider.when('/customer', {
        controller: 'CustomerCtrl',
        templateUrl: 'customer/view/customer.html'
    });

    //Customer Edit
    $routeProvider.when('/customer/:customerId', {
        controller: 'CustomerEditCtrl',
        templateUrl: 'customer/view/customer-edit.html'
    });

}]);

angular
  .module('ng-gulp.customer').service('CustomerService', ['$http',
      function($http) {
          'use strict';

          //REST service abstraction

          //RESTful webservice base URL
          var urlBase = '/services/customer';

          //Fetch all customers
          this.getCustomers = function() {
              return $http.get(urlBase);
          };

          //Fetch customer by ID
          this.getCustomer = function(id) {
              return $http.get(urlBase + '/' + id);
          };

          //Insert new customer
          this.insertCustomer = function(cust) {
              return $http.post(urlBase, cust);
          };

          //Update Customer
          this.updateCustomer = function(cust) {
              return $http.put(urlBase + '/' + cust.id, cust);
          };

          //Abstracts save for insert vs update
          this.save = function(cust) {
            var operation = cust.id ? this.updateCustomer : this.insertCustomer;
            return operation(cust);
          };

          //Delete customer record
          this.deleteCustomer = function(id) {
              return $http.delete(urlBase + '/' + id);
          };
      }
  ]);

angular
    .module('ng-gulp.customer')
    .controller('CustomerEditCtrl', ['$scope', '$location', '$routeParams', 'CustomerService', 'ngDialog',
        function($scope, $location, $routeParams, CustomerService, ngDialog) {

            'use strict';
            init();

            //Initilize Edit view
            function init() {
                getCustomer($routeParams.customerId);
            }

            //Fetch customer by ID
            function getCustomer(id) {
                if (id === 'new') {
                    $scope.customer = {};
                } else {
                    CustomerService.getCustomer(id).success(function(cust) {
                        $scope.customer = cust;
                    }).error(function() {
                        $location.path('/customer');
                    });
                }

            }
            //Navigate back to customer view
            $scope.back = function() {
                $location.path('/customer');
            };

            //Save changes to backend
            $scope.updateCustomer = function() {
                var cust = $scope.customer;

                CustomerService.save(cust)
                    .success(function() {
                        $location.path('/customer');
                        $scope.status = 'Updated Customer! Refreshing customer list.';
                    })
                    .error(function(error) {
                        $scope.status = 'Unable to update customer: ' + error.message;
                    });
            };

            //Delete selected record
            $scope.delete = function() {
                // var rowEntity = ctx.$parent.$parent.row.entity;
                var rowEntity = $scope.customer;
                var confirm = ngDialog.open({
                    template: 'customer/view/confirm.html',
                    className: 'ngdialog-theme-default',
                    plain: false,
                    scope: $scope
                });
                confirm.closePromise.then(function(data) {
                    // console.log('data:', data);
                    // console.log('rowEntity:', rowEntity);
                    if (data.value === 'Yes') {
                        $scope.deleteCustomer(rowEntity.id);
                    }
                });
            };

            //Delete confirmed record
            $scope.deleteCustomer = function(id) {
                CustomerService.deleteCustomer(id)
                    .success(function() {
                        $scope.status = 'Deleted Customer! Refreshing customer list.';
                        $location.path('/customer');
                    })
                    .error(function(error) {
                        $scope.status = 'Unable to delete customer: ' + error.message;
                    });
            };
        }
    ]);

angular
    .module('ng-gulp.customer')
    .controller('CustomerCtrl', ['$scope', '$location','CustomerService',
        function ($scope, $location, CustomerService) {
    'use strict';
    init();

    //Initilize customer view
    function init() {
      getCustomers();
        $scope.columns = [{
            field: 'id',
            width: '10%',
            maxWidth: 200
        }, {
            field: 'name'
        }, {
            field: 'age',
            width: '20%',
            maxWidth: 200
        }, {
            name: 'Action',
            cellTemplate: '<button class="btn btn-primary" ng-click="grid.appScope.edit(this)"><i class="fa fa-pencil"></i></button>',
            width: '20%',
            maxWidth: 200
        }];
        $scope.gridOpts = {
            enableSorting: true,
            enableColumnResizing: true,
            columnDefs: $scope.columns,
            onRegisterApi: function(gridApi) {
              $scope.gridApi = gridApi;
            },
            rowIdentity: function(row) {
              return row.id;
            },
            getRowIdentity: function(row) {
              return row.id;
            }
        };
    }

    //Edit selected record
    $scope.edit = function (ctx) {
      var rowEntity = ctx.$parent.$parent.row.entity;
      $location.path('/customer/' + rowEntity.id);
    };

    //Fetch customer data from backend
    function getCustomers() {
        CustomerService.getCustomers()
            .success(function (custs) {
                $scope.gridOpts.data = custs;
            })
            .error(function (error) {
                $scope.status = 'Unable to load customer data: ' + error.message;
            });
    }

}]);

angular.module('ng-gulp.todo', []).config(['$routeProvider',
    function($routeProvider) {
        'use strict';
        $routeProvider
            .when('/todo', {
                controller: 'TodoCtrl',
                templateUrl: 'todo/view/todo.html'
            });
    }
]);

angular
    .module('ng-gulp.todo')
    .controller('TodoCtrl', ["$scope", "$window", function($scope, $window) {
        'use strict';
        $scope.todos = JSON.parse($window.localStorage.getItem('todos') || '[]');
        $scope.$watch('todos', function(newTodos, oldTodos) {
            if (newTodos !== oldTodos) {
                $window.localStorage.setItem('todos', JSON.stringify(angular.copy($scope.todos)));
            }
        }, true);

        $scope.add = function() {
            var todo = {
                label: $scope.label,
                isDone: false
            };
            $scope.todos.push(todo);
            $window.localStorage.setItem('todos', JSON.stringify(angular.copy($scope.todos)));
            $scope.label = '';
        };

        $scope.check = function() {
            this.todo.isDone = !this.todo.isDone;
        };
    }]);

angular.module('ng-gulp.home', []);

angular
  .module('ng-gulp.home').controller('HomeCtrl', ['$scope',
      function($scope) {
          'use strict';
          $scope.greeting = 'Welcome!';
      }
  ]);

angular.module('ng-gulp', [
    'ngRoute', 'ngResource',
    'ng-gulp.home',
    'ng-gulp.todo',
    'ng-gulp.customer'
])
    .config(['$routeProvider',
        function($routeProvider) {
            'use strict';
            $routeProvider
            .when('/home', {
                controller: 'HomeCtrl',
                templateUrl: 'home/home.html'
            });
            //Default route
            $routeProvider.otherwise({
                redirectTo: '/home'
            });
        }
    ]);

(function(module) {
try {
  module = angular.module('ng-gulp');
} catch (e) {
  module = angular.module('ng-gulp', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('home/home.html',
    '<h3>Home</h3><dl><dt><a href="#/todo" class="btn btn-primary"><i class="fa fa-bars"></i> TODO</a></dt><dd>A Simple TODO application to demo the AngularJS</dd></dl><dl><dt><a href="#/customer" class="btn btn-primary"><i class="fa fa-user"></i> Customer</a></dt><dd>A Simple module to show how to do the CRUD with Angular JS</dd></dl>');
}]);
})();

(function(module) {
try {
  module = angular.module('ng-gulp');
} catch (e) {
  module = angular.module('ng-gulp', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('customer/view/confirm.html',
    '<div class="panel-heading"><h3 class="panel-title">Warning</h3></div><div class="panel-body">You are about to delete a record. Are you sure?<div class="pull-right"><button value="No" ng-click="closeThisDialog(\'No\')" class="btn btn-danger">No</button> <button value="Yes" ng-click="closeThisDialog(\'Yes\')" class="btn btn-success">Yes</button></div></div>');
}]);
})();

(function(module) {
try {
  module = angular.module('ng-gulp');
} catch (e) {
  module = angular.module('ng-gulp', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('customer/view/customer-edit.html',
    '<h3>Customers</h3><div ng-if="status" class="alert alert-info alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button> <strong>Message:</strong>{{status}}</div><form><div class="form-group"><label for="Id">Id</label><input class="form-control" placeholder="ID" ng-model="customer.id" readonly></div><div class="form-group"><label for="name">Name</label><input class="form-control" id="name" placeholder="Name" ng-model="customer.name"></div><div class="form-group"><label for="age">Age</label><input class="form-control" id="age" placeholder="Age" ng-model="customer.age"></div><button class="btn btn-default" ng-click="back()"><i class="fa fa-undo"></i> Cancel</button> <button class="btn btn-primary" ng-click="updateCustomer()"><i class="fa fa-floppy-o"></i> Save</button> <button class="btn btn-danger" ng-if="customer.id" ng-click="delete()"><i class="fa fa-trash-o"></i> Delete</button></form>');
}]);
})();

(function(module) {
try {
  module = angular.module('ng-gulp');
} catch (e) {
  module = angular.module('ng-gulp', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('customer/view/customer.html',
    '<h3>Customers</h3><a href="#/home" class="btn btn-primary"><i class="fa fa-home"></i> Home</a> <a href="#/customer/new" class="btn btn-success"><i class="fa fa-user-plus"></i> Add</a><div ng-if="status" class="alert alert-info alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button> <strong>Message:</strong>{{status}}</div><div id="grid1" ui-grid="gridOpts" class="grid myGrid" ui-grid-resize-columns></div>');
}]);
})();

(function(module) {
try {
  module = angular.module('ng-gulp');
} catch (e) {
  module = angular.module('ng-gulp', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('todo/view/todo.html',
    '<h3>Todo</h3><a href="#/home" class="btn btn-primary"><i class="fa fa-home"></i> Home</a><ul class="todo-list"><li class="todo-item" ng-repeat="todo in todos" ng-class="{\'todo-done\': todo.isDone}"><label><input type="checkbox" ng-click="check()" ng-model="todo.isDone">&nbsp;{{todo.label}}</label></li><li class="todo-item"><form ng-submit="add()"><input placeholder="New item..." ng-model="label"> <button type="submit" ng-disabled="posting || !label">Add</button></form></li></ul>');
}]);
})();
