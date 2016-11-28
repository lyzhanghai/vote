angular.module("mainApp")
.controller("homeController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"SITE_CONFIG",
	"commonService",
	"$sce",
	function(r, s, l, SITE_CONFIG, cs, $sce) {
		r.home.currentTabId = 1;
		cs.getHomeDes(r.voteId)
		.success(function(data){
			if(data.content)
				data.content = data.content.replace(/\.\.\/uploads/g, SITE_CONFIG.IMG_BASE_URL+"/uploads")
				data.content = data.content.replace(/_src=".*?"/g, "");
				s.des = $sce.trustAsHtml(data.content);
		})
	}]
);