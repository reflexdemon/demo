(function() {
    'use strict';

    angular.module('ng-gulp.heat', ['ui.bootstrap-slider']);

})();

(function() {
        'use strict';
        angular
            .module('ng-gulp.heat').service('heatService', HeatService);

        /* @ngInject */
        function HeatService($http) {

            //RESTful webservice base URL
            var urlBase = '/services';

            var service = {
                toFahrenheit: toFahrenheit,
                toCelsius: toCelsius

            };
            return service;

            ///////////////////

            /**
             * Convert from fahrenheit to celsius
             * @param  Number celsius
             * @return Heat model
             */
            function toFahrenheit(celsius) {
                return $http.get(urlBase + '/c2f/' + celsius);
            }
            /**
             * Convert from celsius to fahrenheit
             * @param  Number fahrenheit
             * @return Heat model
             */
            function toCelsius(fahrenheit) {
                return $http.get(urlBase + '/f2c/' + fahrenheit);
            }
        }
        HeatService.$inject = ["$http"];
    }
)();

(function() {
    'use strict';

    angular.module('ng-gulp.customer', ['ui.grid', 'ngDialog', 'ui.grid.resizeColumns'], moduleConfiguration);

    /* @ngInject */
    function moduleConfiguration(ngDialogProvider) {
      ngDialogProvider.setDefaults({
        className: 'panel panel-info',
        plain: true,
        showClose: true,
        closeByDocument: true,
        closeByEscape: true
    });
    }
    moduleConfiguration.$inject = ["ngDialogProvider"];
})();

(function() {
    'use strict';
    angular
        .module('ng-gulp.customer').service('CustomerService', CustomerService);

    /* @ngInject */
    function CustomerService($http) {

        //REST service abstraction

        //RESTful webservice base URL
        var urlBase = '/services/customer';

        var service = {
            getCustomers: getCustomers,
            getCustomer: getCustomer,
            insertCustomer: insertCustomer,
            updateCustomer: updateCustomer,
            save: save,
            deleteCustomer: deleteCustomer
        };
        return service;

        ///////////////////

        //Fetch all customers
        function getCustomers() {
            return $http.get(urlBase);
        }

        //Fetch customer by ID
        function getCustomer(id) {
            return $http.get(urlBase + '/' + id);
        }

        //Insert new customer
        function insertCustomer(cust) {
            return $http.post(urlBase, cust);
        }

        //Update Customer
        function updateCustomer(cust) {
            return $http.put(urlBase + '/' + cust.id, cust);
        }

        //Abstracts save for insert vs update
        function save(cust) {
            var operation = cust.id ? updateCustomer : insertCustomer;
            return operation(cust);
        }

        //Delete customer record
        function deleteCustomer(id) {
            return $http.delete(urlBase + '/' + id);
        }
    }
    CustomerService.$inject = ["$http"];
})();

/**
 * Customer Edit Controller
 */
(function() {
    'use strict';
    //Defenition
    angular
        .module('ng-gulp.customer')
        .controller('CustomerEditCtrl', CustomerEditCtrl);

    // CustomerEditCtrl.$inject = ['$scope', '$location', '$routeParams', 'CustomerService', 'ngDialog'];

    /**
     * [CustomerEditCtrl description]
     * @ngInject
     */
    function CustomerEditCtrl($scope, $location, $routeParams, CustomerService, ngDialog) {
        /*jshint validthis: true */
        var vm = this;
        vm.back = back;
        vm.updateCustomer = updateCustomer;
        vm.deleteRecord = deleteRecord;
        vm.deleteCustomer = deleteCustomer;

        init();


        /**
         * Initilize Edit view
         */
        function init() {
            getCustomer($routeParams.customerId);
        }

        /**
         * Fetch customer by ID
         */
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

        /**
         * Navigate back to customer view
         */
        function back() {
            $location.path('/customer');
        }

        /**
         * Save changes to backend
         */
        function updateCustomer() {
            var cust = $scope.customer;

            CustomerService.save(cust)
                .success(function() {
                    $location.path('/customer');
                    $scope.status = 'Updated Customer! Refreshing customer list.';
                })
                .error(function(error) {
                    $scope.status = 'Unable to update customer: ' + error.message;
                });
        }

        /**
         * Delete selected record
         */
        function deleteRecord() {
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
                    vm.deleteCustomer(rowEntity.id);
                }
            });
        }

        /**
         * Delete confirmed record
         */
        function deleteCustomer(id) {
            CustomerService.deleteCustomer(id)
                .success(function() {
                    $scope.status = 'Deleted Customer! Refreshing customer list.';
                    $location.path('/customer');
                })
                .error(function(error) {
                    $scope.status = 'Unable to delete customer: ' + error.message;
                });
        }
    }
    CustomerEditCtrl.$inject = ["$scope", "$location", "$routeParams", "CustomerService", "ngDialog"];

})();

/**
 * [CustomerCtrl The Controller entry point]
 */
(function() {
    'use strict';

    angular
        .module('ng-gulp.customer')
        .controller('CustomerCtrl', CustomerCtrl);

    // CustomerCtrl.$inject = ['$scope', '$location', 'CustomerService'];

    /**
     * [CustomerCtrl The Controller entry point]
     * @ngInject
     */
    function CustomerCtrl($scope, $location, CustomerService) {
        $scope.editRecord = editRecord;

        init();


        /**
         * Initilize customer view
         */
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
                cellTemplate: '<button class="btn btn-primary" ng-click="grid.appScope.editRecord(this)"><i class="fa fa-pencil"></i></button>',
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

        /**
         * Fetch customer data from backend
         */
        function getCustomers() {
            CustomerService.getCustomers()
                .success(function(custs) {
                    $scope.gridOpts.data = custs;
                })
                .error(function(error) {
                    $scope.status = 'Unable to load customer data: ' + error.message;
                });
        }

        /**
         * Edit the selected customer record
         * @param  {[type]} ctx [description]
         */
        function editRecord(ctx) {
            var rowEntity = ctx.$parent.$parent.row.entity;
            $location.path('/customer/' + rowEntity.id);
        }
    }
    CustomerCtrl.$inject = ["$scope", "$location", "CustomerService"];
})();

(function () {
        'use strict';

  angular.module('ng-gulp.todo', []);

})();

(function() {
    'use strict';

    angular.module('ng-gulp.home', []);

})();

(function() {
    'use strict';

    angular
        .module('ng-gulp.home')
        .config(configure);


    /* @ngInject */
    function configure($routeProvider) {
        $routeProvider
            .when('/todo', {
                controller: 'TodoCtrl',
                templateUrl: 'todo/view/todo.html'
            });
    }
    configure.$inject = ["$routeProvider"];

})();

(function() {
    'use strict';

    angular
        .module('ng-gulp.todo')
        .controller('TodoCtrl', TodoCtrl);

        /* @ngInject */
        function TodoCtrl($scope, $window) {
                $scope.todos = JSON.parse($window.localStorage.getItem('todos') || '[]');
                $scope.$watch('todos', todosListner, true);

                $scope.add = function() {
                    var todo = {
                        label: $scope.label,
                        isDone: false
                    };
                    $scope.todos.push(todo);
                    $window.localStorage.setItem('todos', JSON.stringify(angular.copy($scope.todos)));
                    $scope.label = '';
                };

                $scope.check = function(ctx) {
                    ctx.isDone = !ctx.isDone;
                };

                $scope.removeChecked = function() {
                    var todos = [];
                    for (var i = $scope.todos.length - 1; i >= 0; i--) {
                      if (!$scope.todos[i].isDone) {
                        todos.push($scope.todos[i]);
                      }
                    }
                    $scope.todos = todos;
                    $window.localStorage.setItem('todos', JSON.stringify(angular.copy($scope.todos)));
                };

                function todosListner(newTodos, oldTodos) {
                    if (newTodos !== oldTodos) {
                        $window.localStorage.setItem('todos', JSON.stringify(angular.copy($scope.todos)));
                    }
                }
            }
            TodoCtrl.$inject = ["$scope", "$window"];

})();

(function() {
    'use strict';

    angular
        .module('ng-gulp.home')
        .config(configure);


    /* @ngInject */
    function configure($routeProvider) {
        $routeProvider
            .when('/home', { //Default
                controller: 'HomeCtrl',
                templateUrl: 'home/home.html'
            }).when('/module', {
                controller: 'HomeCtrl',
                templateUrl: 'home/module.html'
            }).when('/stack', {
                controller: 'HomeCtrl',
                templateUrl: 'home/stack.html'
            });
        //Default route
        $routeProvider.otherwise({
            redirectTo: '/home'
        });
    }
    configure.$inject = ["$routeProvider"];

})();

(function() {
    'use strict';
    angular.module('ng-gulp.home').directive('navHeader', navHeader);

    /* @ngInject */
    function navHeader() {
        return {
            restrict: 'E',
            replace: true,
            transclude: true,
            scope: true,
            templateUrl: 'home/nav-header.html',
            controller: NavCtrl
        };
    }
    /* @ngInject */
    function NavCtrl($scope, $element, $location) {
        $scope.isActive = function(viewLocation) {
            var active = false;
            if (viewLocation.length) {
                if (viewLocation === $location.path()) {
                    active = true;
                }
            } else {
                for (var i = 0; i < viewLocation.length; i++) {
                    var l = viewLocation[i];
                    if (l === $location.path()) {
                        active = true;
                        break;
                    }
                }
            }
            return active;

        };
    }
    NavCtrl.$inject = ["$scope", "$element", "$location"];

})();

(function() {
    'use strict';
    angular
        .module('ng-gulp.home').controller('HomeCtrl', HomeCtrl);

    /* @ngInject */
    function HomeCtrl($scope) {
        $scope.greeting = 'Welcome!';
    }
    HomeCtrl.$inject = ["$scope"];
})();

(function() {
    'use strict';

    angular
        .module('ng-gulp.customer')
        .config(configure);


    /* @ngInject */
    function configure($routeProvider) {
        //Heat module
        $routeProvider
            .when('/heat', {
                controller: 'HeatCtrl',
                templateUrl: 'heat/view/heat.html',
                controllerAs: 'vm'
            });
    }
    configure.$inject = ["$routeProvider"];

})();

/**
 * Controller
 */
(function() {
    'use strict';
    //Defenition
    angular
        .module('ng-gulp.heat').controller('HeatCtrl', HeatCtrl);

    // HeatCtrl.$inject = ['$scope', 'HeatService'];

    /**
     * [HeatCtrl description]
     * @ngInject
     */
    function HeatCtrl($scope, heatService) {
        /*jshint validthis: true */
        var vm = this;
        vm.f2c = f2c;
        vm.c2f = c2f;
        init();


        /**
         * Inits the ctrl.
         */
        function init() {
            $scope.defaultValue = {
                celsius: 0.0,
                fahrenheit: 0.0
            };
            $scope.testOptions = {
                min: -273,
                step: 1,
                max: 273,
                value: 0,
                tooltip: 'show'
            };
            $scope.heat = $scope.defaultValue;
        }

        function f2c() {
            heatService.toCelsius($scope.heat.fahrenheit).success(function(heat) {
                $scope.heat = heat;
            }).error(function(error) {
                $scope.messsage = error;
                $scope.heat = $scope.defaultValue;
            });
        }

        function c2f() {
            heatService.toFahrenheit($scope.heat.celsius).success(function(heat) {
                $scope.heat = heat;
            }).error(function(error) {
                $scope.messsage = error;
                $scope.heat = $scope.defaultValue;
            });
        }

    }
    HeatCtrl.$inject = ["$scope", "heatService"];
})();

(function() {
    'use strict';

    angular
        .module('ng-gulp.customer')
        .config(configure);


    /* @ngInject */
    function configure($routeProvider) {
        //Customer
        $routeProvider.when('/customer', {
            controller: 'CustomerCtrl',
            templateUrl: 'customer/view/customer.html',
            controllerAs: 'vm'
        });

        //Customer Edit
        $routeProvider.when('/customer/:customerId', {
            controller: 'CustomerEditCtrl',
            templateUrl: 'customer/view/customer-edit.html',
            controllerAs: 'vm'
        });
    }
    configure.$inject = ["$routeProvider"];

})();

(function() {
    'use strict';

    angular.module('ng-gulp', [
        'ngRoute', 'ngResource',
        'ng-gulp.home',
        'ng-gulp.todo',
        'ng-gulp.customer',
        'ng-gulp.heat',
        'angulartics',
        'angulartics.google.analytics'
    ]);
})();

(function(module) {
try {
  module = angular.module('ng-gulp');
} catch (e) {
  module = angular.module('ng-gulp', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('home/home.html',
    '<div class="jumbotron"><h2>Angular Gulp Fusion</h2><p>Clone <i class="fa fa-github-alt"></i> or <i class="fa fa-code-fork"></i> Fork the project and send us a pull request!.</p><p><a href="http://reflexdemon.github.io/ng-gulp" class="btn btn-default" target="_blank"><i class="fa fa-github-square"></i> Github Pages</a></p></div>');
}]);
})();

(function(module) {
try {
  module = angular.module('ng-gulp');
} catch (e) {
  module = angular.module('ng-gulp', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('home/module.html',
    '<div class="well"><h2>Modules</h2><dl><dt><a href="#/todo" class="btn btn-info"><i class="fa fa-bars"></i> TODO</a></dt><dd>A Simple TODO application to demo the AngularJS</dd></dl><dl><dt><a href="#/heat" class="btn btn-danger"><i class="fa fa-refresh fa-spin"></i> Temperature Conversion Utility</a></dt><dd>A Simple module to show how to make server call for non persistance service calls</dd></dl><dl><dt><a href="#/customer" class="btn btn-primary"><i class="fa fa-user"></i> Customer</a></dt><dd>A Simple module to show how to do the CRUD with Angular JS</dd></dl></div>');
}]);
})();

(function(module) {
try {
  module = angular.module('ng-gulp');
} catch (e) {
  module = angular.module('ng-gulp', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('home/nav-header.html',
    '<div class="navbar navbar-inverse navbar-fixed-top"><div class="container"><div class="navbar-header"><a class="navbar-brand" href="#/home"><i class="fa fa-beer"></i> NG Gulp</a></div><ul class="nav navbar-nav"><li ng-class="{ active: isActive(\'/home\') }"><a href="#/home"><i class="fa fa-home"></i> Home</a></li><li class="dropdown"><a href="#/module" ng-class="{ active: isActive(\'/module\') }" data-toggle="dropdown" role="button" aria-expanded="true"><i class="fa fa-magic"></i> Modules <span class="caret"></span></a><ul class="dropdown-menu" role="menu"><li ng-class="{ active: isActive(\'/todo\') }"><a href="#/todo"><i class="fa fa-bars"></i> TODO</a></li><li class="divider"></li><li ng-class="{ active: isActive(\'/heat\') }"><a href="#/heat"><i class="fa fa-refresh fa-spin"></i> Temperature Conversion Utility</a></li><li ng-class="{ active: isActive(\'/customer\') }"><a href="#/customer"><i class="fa fa-user"></i> Customer</a></li></ul></li><li ng-class="{ active: isActive(\'/stack\') }"><a href="#/stack"><i class="fa fa-terminal"></i> Tech Stack</a></li><li class="dropdown-menu-right"><a href="https://github.com/reflexdemon/ng-gulp" target="_blank"><i class="fa fa-github-square"></i> GitHub</a></li></ul></div></div>');
}]);
})();

(function(module) {
try {
  module = angular.module('ng-gulp');
} catch (e) {
  module = angular.module('ng-gulp', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('home/stack.html',
    '<div class="well"><dl><dt><i class="fa fa-terminal"></i> Technology Stack</dt><dd><ul><li><a target="_blank" href="https://angularjs.org/">Angular JS</a><ul><li>v1.4</li></ul></li><li><a target="_blank" href="http://gulpjs.com/">Gulp JS</a><ul><li>v3.8.11</li></ul></li><li><a target="_blank" href="http://getbootstrap.com/">Bootstrap</a><ul><li>v3.3.2</li></ul></li><li><a target="_blank" href="http://fontawesome.io/">Fontawesome</a><ul><li>v4.3.0</li></ul></li><li><a target="_blank" href="http://lesscss.org/">LESS</a><ul><li>v2.4.0</li></ul></li></ul></dd></dl></div><div class="well"><dl><dt><i class="fa fa-puzzle-piece"></i> Addons</dt><dd><ul><li><a target="_blank" href="https://github.com/angular/bower-angular-route">Angular Route</a></li><li><a target="_blank" href="https://github.com/angular/bower-angular-resource">Angular Resource</a></li><li><a target="_blank" href="http://ui-grid.info/">Angular UI Grid</a></li><li><a target="_blank" href="http://likeastore.github.io/ngDialog/">NG Dialog</a></li><li><a target="_blank" href="https://github.com/seiyria/angular-bootstrap-slider">Angular Bootstrap Slider</a></li><li><a target="_blank" href="http://luisfarzati.github.io/angulartics/">Angulartics</a></li></ul></dd></dl></div><div class="well"><dl><dt><i class="fa fa-info"></i> More Links</dt><dd><ul><li><a target="_blank" href="https://github.com/johnpapa/angular-styleguide"><i class="fa fa-road"></i> Next Steps to Style guide</a></li></ul></dd></dl></div>');
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
    '<h3>Customers</h3><div ng-if="status" class="alert alert-info alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button> <strong>Message:</strong>{{status}}</div><form><div class="form-group"><label for="Id">Id</label><input class="form-control" placeholder="ID" ng-model="customer.id" readonly></div><div class="form-group"><label for="name">Name</label><input class="form-control" id="name" placeholder="Name" ng-model="customer.name"></div><div class="form-group"><label for="age">Age</label><input class="form-control" id="age" placeholder="Age" ng-model="customer.age"></div><button class="btn btn-default" ng-click="vm.back()"><i class="fa fa-undo"></i> Cancel</button> <button class="btn btn-primary" ng-click="vm.updateCustomer()"><i class="fa fa-floppy-o"></i> Save</button> <button class="btn btn-danger" ng-if="customer.id" ng-click="vm.deleteRecord()"><i class="fa fa-trash-o"></i> Delete</button></form>');
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
    '<h3><i class="fa fa fa-user"></i> Customers</h3><a href="#/customer/new" class="btn btn-success"><i class="fa fa-user-plus"></i> Add</a><div ng-if="status" class="alert alert-info alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button> <strong>Message:</strong>{{status}}</div><div id="grid1" ui-grid="gridOpts" class="grid myGrid" ui-grid-resize-columns></div>');
}]);
})();

(function(module) {
try {
  module = angular.module('ng-gulp');
} catch (e) {
  module = angular.module('ng-gulp', []);
}
module.run(['$templateCache', function($templateCache) {
  $templateCache.put('heat/view/heat.html',
    '<h3><i class="fa fa-refresh fa-spin"></i> Temperature Conversion Utility</h3><div ng-if="status" class="alert alert-info alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button> <strong>Message:</strong>{{status}}</div><div class="row"><div class="well"><form class="form-horizontal"><div class="form-group"><label for="fahrenheit">Fahrenheit</label><slider ng-model="heat.fahrenheit" min="testOptions.min" step="testOptions.step" max="testOptions.max" value="testOptions.value" change="f2c()"></slider><label>{{heat.fahrenheit}}</label><button type="button" class="btn btn-default" ng-click="vm.f2c()">Fahrenheit to Celsius</button></div><div class="form-group"><label for="celsius">Celsius</label><slider ng-model="heat.celsius" min="testOptions.min" step="testOptions.step" max="testOptions.max" value="testOptions.value" change="c2f()"></slider><label>{{heat.celsius}}</label><button type="button" class="btn btn-default" ng-click="vm.c2f()">Celsius to Fahrenheit</button></div></form></div></div>');
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
    '<h3><i class="fa fa-bars"></i> Todo</h3><ul class="todo-list"><li class="todo-item" ng-repeat="todo in todos" ng-class="{\'todo-done\': todo.isDone}"><label><input type="checkbox" ng-click="check(this)" ng-model="todo.isDone">&nbsp;{{todo.label}}</label></li><li class="todo-item"><form ng-submit="add()"><input placeholder="New item..." ng-model="label"> <button type="submit" ng-disabled="posting || !label" class="btn btn-success"><i class="fa fa-plus-circle"></i> Add</button></form></li></ul><button type="button" ng-click="removeChecked()" class="btn btn-danger"><i class="fa fa-minus-circle"></i> Remove Completed</button>');
}]);
})();
