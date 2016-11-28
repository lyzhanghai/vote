var app = angular.module("mainApp", ["ngRoute", "ngSanitize", "configModule", "resourceModule", "angular-carousel", "wu.masonry"]);
app.config([
            "$routeProvider",
            "$locationProvider", 
            function(r, l) {
            	l.html5Mode(true);
	r.when("/:voteId", {name: "home", templateUrl: "partials/home.html", controller: "homeController"})
	.when("/:voteId/home", {name: "home", templateUrl: "partials/home.html", controller: "homeController"})
	.when("/:voteId/rank", {name: "rank", templateUrl: "partials/rank.html", controller: "rankController"})
	.when("/:voteId/entry", {name: "entry", templateUrl: "partials/entry.html", controller: "entryController"})
	.when("/:voteId/detail/:playerId", {name: "detail", templateUrl: "partials/detail.html", controller: "detailController"})
	.when("/:voteId/srdz", {name: "srdz", templateUrl: "partials/srdz.html", controller: "homeController"})
	.when("/:voteId/404",{name:"404",templateUrl:"partials/404.html", controller:"404Controller"})
	.otherwise({redirectTo:"/0/404"})
}])
app.run(["$rootScope", "$location", "$timeout", "commonService", "SITE_CONFIG", function(r, $location, $timeout, commonService, SITE_CONFIG) {
	var g=document.createElement("script");
	g.src="//res.wx.qq.com/open/js/jweixin-1.0.0.js",
	g.async=!1;
	var f=document.getElementsByTagName("script")[0];
	f.parentNode.insertBefore(g,f);
	
	r.page = {
			showHeader : SITE_CONFIG.SHOW_HEADER,
			showFooter : SITE_CONFIG.SHOW_FOOTER,
			baseUrl : SITE_CONFIG.BASE_URL
	}
	r.message = {show:false, type:1, text:""}; 
	r.showDetail = function(entry) {
		$location.path("/"+r.voteId+"/detail/"+entry.id)
	}
	r.showMessage = function(type, text, callback) {
		r.message.show=true;
		r.message.type=type;
		r.message.text=text;
		r.message.callback=callback;
	}
	r.closeMessage = function() {
		r.message.show = false;
	}
	var refreshVoteInfo = function() {
		commonService.getVoteInfo(r.voteId)
		.success(function(data){
			r.voteInfo = {};
			r.voteInfo.entrycount = data.entrycount;
			r.voteInfo.votecount = data.votecount;
			r.voteInfo.visitcount = data.visitcount;
			$timeout(refreshVoteInfo, 5000);
		})	
	}
	r.$on("$routeChangeStart", function(evt, next, previous) {
		if(!r.voteId&&next.params.voteId) {
			r.voteId = next.params.voteId;
		}
		if(!r.actionInfo){
			commonService.getActionInfo(r.voteId)
			.success(function(data){
				data.account.qrcode=(r.BASE_URL+"/img/qrcode/"+data.account.id+".jpg");
				r.actionInfo=data;
				console.log("succ")
				r.$broadcast("actionInfoSucc");
			});
		} else {
			console.log("succ")
			r.$broadcast("actionInfoSucc");
		}
		if(!r.homeTabs&&(menus=commonService.getMenus())) {
			menus.forEach(function(menu) {
				if(menu.type==1)
					menu.url = "/"+r.voteId+menu.url;	
			})
			r.homeTabs=menus;
		}
		if(!r.voteInfo) {
			refreshVoteInfo();
		}
	})
	r.home = r.home||{currentTabId : 1};
	r.switchTab=function(index) {
		var t = r.homeTabs[index];
		if(t.type==2) {
			location.href=t.url;
		} else if(t.type==1) {
			r.home.currentTabId=t.id;
			$location.path(t.url);
			$location.replace();
		}
	}
}])
