angular.module("mainApp")
.controller("rankController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"SITE_CONFIG",
	"commonService",
	function(r, s, l, SITE_CONFIG, cs) {
		r.home.currentTabId = 2;
		s.page = 1;
		s.orderBy = 1;
		s.vote = function(entry){
			if(!r.actionInfo.user.subscribe) {
				r.showMessage(2, "");
				return;
			}
			cs.vote(entry.id)
			.success(function(data){
				if(data.success){
					r.showMessage(1, "投票成功");
					entry.count++;
				} else {
					r.showMessage(1, data.errmsg);
				}
			})
			.error(function(data) {
				r.showMessage(1, "投票失败");
			})
		};
		s.loadEntry = function(page) {
			cs.getEntryList(r.voteId, page, s.condition, s.orderBy)
			.success(function(data) {
				if(data.items&&data.items.length==0) {
					rs.showMessage(1, "没有更多了T^T")
				} else {
					data.items&&data.items.length>0&&data.items.forEach(function(item) {
						item.picUrl=item.picUrl.replace("uploads", SITE_CONFIG.IMG_BASE_URL+"/uploads")
						s.entrys.push(item);
					})
				}
			});
		}
		s.search = function() {
			s.entrys=[];
			s.page = 1;
			s.loadEntry(s.page);
		}
		s.getMore = function() {
			s.loadEntry(++s.page);
		}
		s.search();
	}]
);