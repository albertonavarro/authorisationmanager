<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Demo</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width"/>
    <base href="/"/>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/winchan/winchan.js"></script>

</head>
    <body ng-app="app" ng-controller="home as home">
        <h1>Login</h1>
        <div class="container" ng-show="!home.authenticated">
            <div>
                With Facebook: <a href="/login/facebook">click here</a>
            </div>
            <div>
                With Github: <a href="/login/github">click here</a>
            </div>
        </div>
        <div class="container" ng-show="home.authenticated">
            Logged in as: <span ng-bind="home.user"></span>
            <div>
                <button ng-click="home.logout()" class="btn btn-primary">Logout</button>
            </div>
            <button>close</button>
        </div>
        <script type="text/javascript" src="/webjars/angularjs/angular.min.js"></script>
        <script type="text/javascript">
          angular.module("app", []).controller("home", function($http) {
            var self = this;
            $http.get("/user").success(function(data) {
              self.user = data.userAuthentication.details.name;
              self.authenticated = true;
            }).error(function() {
              self.user = "N/A";
              self.authenticated = false;
            });

            self.logout = function() {
              $http.post('/logout', {}).success(function() {
                self.authenticated = false;
                $location.path("/");
              }).error(function(data) {
                console.log("Logout failed")
                self.authenticated = false;
              });
            };
          });
        </script>

        <script type="text/javascript">

         WinChan.onOpen(function(origin, args, cb) {
          cb({
            "these things": "xxx"
          });
          window.close();
        });
            </script>
    </body>
</html>