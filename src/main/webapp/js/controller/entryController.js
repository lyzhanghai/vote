angular.module("mainApp")
.controller("entryController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"commonService",
	"wxService",
	"$routeParams",
	function(r, s, l, cs, ws, rp) {
		s.$on("actionInfoSucc", function() {
			console.log(r.home.currentTabId)
			if(r.home.currentTabId==3)
				ws.config(["chooseImage", "previewImage", "uploadImage"]);
		})
		var init = function() {
			r.home.currentTabId = 3;
			s.params = cs.getEntryParams();
			s.tips = "（若在线报名失败，可以将报名信息：";
			s.params.forEach(function(param, index, b, c) {
				if(index==0)
					s.tips+=param.text;
				else
					s.tips=s.tips+"+"+param.text;
			})
			s.tips += " 发到邮箱：huyuanyuan@lexiangapp.cn）";
		}
		s.chooseImage = function() {
			ws.chooseImage(9, function(res) {
				if(!s.images) s.images=[]
				res.localIds.forEach(function(localId) {
					var image = {localId:localId};
					ws.uploadImage(localId, function(serverId) {
						image.serverId = serverId;
					})
					s.images.push(image)
				});
			})
		}
		s.previewImage = function(src) {
			var urls = []
			s.images.forEach(function(image) {
				urls.push(images.localId)
			})
			ws.previewImage(src, urls);
		}
		s.entrySubmit = function() {
			var data={};
			console.log("entrySubmit")
			var error;
			s.params.forEach(function(param) {
				if(error)
					return
				if(!param.value) {
					error={field:param.text,reason:"不能为空"};
					return;
				}
				data[param.name]=param.value;
			})
			if(error) {
				r.showMessage(1, error.field+error.reason);
				return
			}
			cs.entry(data).success(function(entry) {
				if(entry.success) {
					r.showMessage(1, "报名成功", function() {r.showDetail(entry)});
				} else {
					r.showMessage(2, "报名失败");	
				}
			}).error(function() {
				r.showMessage(2, "报名失败");
			});
		}
		init();
	}]
);