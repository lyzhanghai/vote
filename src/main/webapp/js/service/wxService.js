angular.module("resourceModule")
.factory("wxService", ["$http", "$location", "$rootScope", "SITE_CONFIG", function(h, $location, rs, SITE_CONFIG) {
	var a = SITE_CONFIG.BASE_URL+SITE_CONFIG.API_URL;
	return {
		config:function(jsapiList) {
			h({method:"GET",url:a+"/wxAccount_jssdkConfig", params:{accountId:rs.actionInfo.account.id, url:$location.absUrl()}})
			.success(function(data) {
				if(data.appId&&data.timestamp&&data.nonceStr&&data.signature) {
					wx.config({
					    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
					    appId: data.appId, // 必填，公众号的唯一标识
					    timestamp: data.timestamp, // 必填，生成签名的时间戳
					    nonceStr: data.nonceStr, // 必填，生成签名的随机串
					    signature: data.signature,// 必填，签名，见附录1
					    jsApiList: jsapiList // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
					});
					wx.ready(function(){
						wx.checkJsApi({
						    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
						    success: function(res) {
						    	alert("ready:"+res)
						        // 以键值对的形式返回，可用的api值true，不可用为false
						        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
						    }
						});
					    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
					});
					wx.error(function(res){
						alert("error:"+res)
					    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
					});
				}
			})
		},
		chooseImage:function(count, success) {
			wx.chooseImage({
			    count: count, // 默认9
			    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
			    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
			    success: success // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
			});
		},
		previewImage:function(current, list) {
			wx.previewImage({
			    current: current, // 当前显示图片的http链接
			    urls: list // 需要预览的图片http链接列表
			});
		},
		uploadImage:function(localId, success){
			wx.uploadImage({
				localId: '', // 需要上传的图片的本地ID，由chooseImage接口获得
				isShowProgressTips: 1, // 默认为1，显示进度提示
				success: success// 返回图片的服务器端ID serverId
			});
		},
		onMenuShareAppMessage:function() {
			wx.onMenuShareAppMessage({
			    title: '', // 分享标题
			    desc: '', // 分享描述
			    link: '', // 分享链接
			    imgUrl: '', // 分享图标
			    type: '', // 分享类型,music、video或link，不填默认为link
			    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			    success: function () { 
			        // 用户确认分享后执行的回调函数
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    }
			});
		}
	}
}])