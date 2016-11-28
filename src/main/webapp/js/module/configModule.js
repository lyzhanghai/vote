var config = angular.module("configModule", [])
.constant("SITE_CONFIG", {
	SHOW_FOOTER: true,
	SHOW_HEADER: true,
	BASE_URL: "/vote",
	API_URL:"/api",
	IMG_BASE_URL:"http://www.51xiangshiguang.com/wx"
});