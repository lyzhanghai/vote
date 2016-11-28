angular.module("resourceModule")
.factory("commonService", ["$http", "$rootScope", "SITE_CONFIG", function(h, rs, SITE_CONFIG) {
	var a = SITE_CONFIG.BASE_URL+SITE_CONFIG.API_URL;
	return {
		getEntryParams:function() {
			return [{"text":"姓名", "form_text":"姓名：", "type":1, "name":"name"},
			        {"text":"电话", "form_text":"电话：", "type":1, "name":"phone"},
			        {"text":"年龄", "form_text":"年龄：", "type":1, "name":"des"},
			        {"text":"参赛照片", "form_text":"参赛　<br>照片：", "type":2, "name":"picMedidIds"}]
		},
		getBanners:function() {
			return [{"id":1, "linkUrl":"", "picUrl":"http://www.51xiangshiguang.com/vote/vote/img/banner-1.jpg", "title":"享食光，一个优质的生活美食馆", "sort":1},
			        {"id":2, "linkUrl":"", "picUrl":"http://www.51xiangshiguang.com/vote/vote/img/banner-2.jpg", "title":"寻找最美精油代言人选秀大赛", "sort":2},
			        {"id":3, "linkUrl":"", "picUrl":"http://www.51xiangshiguang.com/vote/vote/img/banner-3.jpg", "title":"杭州鑫航影视传媒有限公司", "sort":3},
			        {"id":4, "linkUrl":"", "picUrl":"http://www.51xiangshiguang.com/vote/vote/img/banner-4.jpg", "title":"一百年不许变，家和家天下", "sort":4},
			        {"id":5, "linkUrl":"", "picUrl":"http://www.51xiangshiguang.com/vote/vote/img/banner-5.jpg", "title":"健康的精油才是好精油", "sort":5}]
		},
		getMenus:function() {
			return [{id:1, name:"首页", icon:"float_nav1", url:"/home/", type:1},
		            {id:2, name:"排名", icon:"float_nav2", url:"/rank/", type:1},
		            {id:3, name:"投票", icon:"float_nav3", url:"/entry/", type:1},
		            {id:4, name:"享食光", icon:"float_nav4", url:"http://www.51xiangshiguang.com/srdz/wpt/startJourney?account.pkey=10", type:2}];
		},
		getHomeDes:function(voteId) {
			return h({method:"GET",url:a+"/vote_homeDes",params:{voteId:voteId}});
		},
		getVoteInfo:function(voteId) {
			return h({method:"GET", url:a+"/vote_voteInfo",params:{voteId:voteId}});
		},
		getActionInfo:function(voteId) {
			return h({method:"GET",url:a+"/vote_info",params:{voteId:voteId}});
		},
		getEntryList:function(voteId, page, condition, orderBy) {
			var limit = 6;
			var start = (page-1)*limit
			return h({method:"GET", url:a+"/voteEntry_entryList", params:{voteId:voteId,start:start,limit:limit,condition:condition,orderBy:orderBy}});
		},
		getEntryList2:function(page) {
			var data = [{"id":1, "number":1, "record":10,"picUrl":"http://lorempixel.com/400/600/abstract"},
					       {"id":2, "number":2, "record":10,"picUrl":"http://lorempixel.com/400/300/technics"},
					       {"id":3, "number":3, "record":10,"picUrl":"http://lorempixel.com/400/500/sports"},
					       {"id":4, "number":4, "record":10,"picUrl":"http://lorempixel.com/400/300/nightlife"},
					       {"id":5, "number":5, "record":10,"picUrl":"http://lorempixel.com/450/700/city"},
					       {"id":6, "number":6, "record":10,"picUrl":"http://lorempixel.com/410/350/sports"},
					       {"id":7, "number":7, "record":10,"picUrl":"http://lorempixel.com/400/300/nature"},
					       {"id":8, "number":8, "record":10,"picUrl":"http://lorempixel.com/400/300/people"},
					       {"id":9, "number":9, "record":10,"picUrl":"http://lorempixel.com/400/300/transport"},
					       {"id":10, "number":10, "record":10,"picUrl":"http://lorempixel.com/480/300/technics"},
					       {"id":11, "number":11, "record":10,"picUrl":"http://lorempixel.com/450/300/fashion"},
					       {"id":12, "number":12, "record":10,"picUrl":"http://lorempixel.com/420/300/abstract"}];
			var limit = 3;
			return {
				total:data.length,
				items:data.slice((page-1)*limit, page*limit)
			}			
		},
		getDetail:function(playerId) {
			return h({method:"GET",url:a+"/voteEntry_detail",params:{entryId:playerId}});
		},
		vote:function(playerId) {
			return h({method:"GET",url:a+"/voteEntry_vote", params:{entryId:playerId}});
			//return {"success":true, "id":1, "name":"媛媛", "number":3, "count":11, "rank":1, "picUrls":["http://lorempixel.com/400/300/people","http://lorempixel.com/400/300/people","http://lorempixel.com/400/300/people"]};
		},
		entry:function(param) {
			var params =param||{}
			params.voteId = rs.voteId;
			return h({method:"GET", url:a+"/voteEntry_entry"}, param);
		}
	}
}])