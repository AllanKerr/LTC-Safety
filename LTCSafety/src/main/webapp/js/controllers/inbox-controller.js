
/**
 * The inbox controller is responsible for managing the concerns in the admin inbox.
 * This functions in this controller are responsible for querying for concerns
 * and switching between concern pages.
 */
safetyApp.controller('InboxCtrl', function InboxCtrl($scope, $location, $routeParams, firebase, adminApi) {

    /**
     * The list of concerns that is displayed in the current inbox page.
     * @type {Array}
     */
    $scope.concerns = [];

    /**
     *  The auth value used for accessing the current user.
     *  All functions in this controller require the user to be authenticated.
     */
    $scope.auth = firebase.auth();

    /**
     * The concern request used to populate the current concerns list.
     *
     * accessToken: The users Firebase access token to verify that the request is authenticated
     * limit: The number of concerns that should be returned in the request
     * page: The index of the concern that the request should start at.
     *
     * @type {{accessToken: null, limit: Number, offset: Number}}
     */
    $scope.concernRequest = {
        accessToken : null,
        limit : parseInt($routeParams.limit),
        offset : parseInt($routeParams.page)
    };

    /**
     * The callback when the user auth state changes causing the list of concerns to be updated.
     */
    $scope.auth.onAuthStateChanged(function (firebaseUser) {

        if (firebaseUser) {

            // Update the concern request to have the most recent token
            const promise = firebaseUser.getToken();
            promise.then(function (rawToken) {
                $scope.concernRequest.accessToken = rawToken;

                // Update the list of concerns
                $scope.updateConcernList();
            });
        }
    });


    /**
     * Updates the existing concern list by querying the Admin API for the most recent set of concerns.
     *
     * @precond $scope.concernRequest.accessToken is a valid Firebase token
     * @precond $scope.concernRequest.accessToken != null
     */
    $scope.refresh = function () {

        if ($scope.concernRequest.accessToken == null) {
            console.log("Attempted to refresh the concerns list with a null access token.");
            return;
        }
        $scope.updateConcernList();
    };

    /**
     * Fetches the concerns in the next page by querying the Admin API starting at the end of the current page.
     *
     * @precond $scope.concernRequest.accessToken is a valid Firebase token
     * @precond $scope.concernRequest.accessToken != null
     */
    $scope.nextPage = function () {

        if ($scope.concernRequest.accessToken == null) {
            console.log("Attempted to fetch the next page of the concerns list with a null access token.");
            return;
        }
        var nextOffset = +$scope.concernRequest.offset + +$scope.concernRequest.limit;
        $location.url('/inbox/' + nextOffset + '/' + $scope.concernRequest.limit);
    };

    /**
     * Fetches the concerns in the previous page by querying the Admin API starting before the start of the current page.
     *
     * @precond $scope.concernRequest.accessToken is a valid Firebase token
     * @precond $scope.concernRequest.accessToken != null
     * @precond $scope.concernRequest.offset >= $scope.concernRequest.limit
     */
    $scope.previousPage = function () {

        if ($scope.concernRequest.accessToken == null) {
            console.log("Attempted to fetch the next page of the concerns list with a null access token.");
            return;
        }

        var nextOffset = $scope.concernRequest.offset - $scope.concernRequest.limit;
        if (nextOffset < 0) {
            console.log("Attempted to fetch a page with a negative start index.");
            return;
        }
        $location.url('/inbox/' + nextOffset + '/' + $scope.concernRequest.limit);
    };

    /**
     * Direct to specific concern-detail page
     *
     * @precond id is not null
     */
    $scope.clickMe = function (concern) {
        if(concern == null) {
            console.log("There is no id found");
        }
        var identifier = concern.id;
        console.log(identifier);
        $location.url('/concern-detail/' + identifier);
    };

    /**
     * Updates the list of concerns using the existing concern request.
     *
     * @precond $scope.concernRequest.accessToken is a valid Firebase token
     * @precond $scope.concernRequest.accessToken != null
     * @precond $scope.concernRequest.offset >= 0
     * @precond $scope.concernRequest.limit > 0
     */
    $scope.updateConcernList = function () {

        if ($scope.concernRequest.accessToken == null) {
            console.log("Attempted to fetch the concerns list with a null access token.");
            return;
        }
        if ($scope.concernRequest.offset < 0) {
            console.log("Attempted to fetch a page with a negative start index.");
            return;
        }
        if ($scope.concernRequest.limit <= 0) {
            console.log("Attempted to fetch an empty page.");
            return;
        }
        adminApi.requestConcernList($scope.concernRequest).execute(
            function (resp) {
                $scope.concerns = resp.items;
                $scope.$apply();
            }
        );
    };
});