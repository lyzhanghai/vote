angular.module("mainApp")
.controller("detailController", [
	"$rootScope",
	"$scope",
	"SITE_CONFIG",
	"commonService",
	"$routeParams",
	function(r,s, SITE_CONFIG, cs,rp) {
		s.$on("actionInfoSucc", function() {
			//ws.config(["chooseImage", "previewImage", "uploadImage"]);
		})
		cs.getDetail(rp.playerId)
		.success(function(data){
			if(data.picUrls&&data.picUrls.length>0){
				data.picUrls.forEach(function(picUrl) {
					picUrl.picUrl = picUrl.picUrl.replace("uploads", SITE_CONFIG.IMG_BASE_URL+"/uploads");
				})
			}
			s.detail = data;
		})
		r.home.currentTabId = 2;
		
		s.signUp = function() {
			r.switchTab(2);
		};
		s.vote = function(){
			if(!r.actionInfo.user.subscribe) {
				r.showMessage(2, "");
				return;
			}
			cs.vote(s.detail.id)
			.success(function(data){
				if(data.success){
					r.showMessage(1, "投票成功");
					s.detail.count=data.count;
					s.detail.rank=data.rank
				} else {
					r.showMessage(1, data.errmsg);
				}
			})
			.error(function(data) {
				r.showMessage(1, "投票失败");
			})
		};
	}]
);