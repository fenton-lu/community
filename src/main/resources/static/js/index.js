$(function(){
	$("#publishBtn").click(publish);
});

function publish() {
	$("#publishModal").modal("hide");

	// 如果启动csrf，每次异步请求都需要处理，不然得不到token会认为被攻击了

	// 发送AJAX请求之前,将CSRF令牌设置到请求的消息头中.
//    var token = $("meta[name='_csrf']").attr("content");
//    var header = $("meta[name='_csrf_header']").attr("content");
//    $(document).ajaxSend(function(e, xhr, options){
//        xhr.setRequestHeader(header, token);
//    });

	// 获取标题和内容
	var title = $("#recipient-name").val();
	var content = $("#message-text").val();
	// 发送异步请求(POST)
	$.post(
		CONTEXT_PATH + "/discuss/add",
		{"title":title, "content":content},
		function(data){
			// 将格式完好的JSON字符串转为与之对应的JavaScript对象
			data = $.parseJSON(data);
			// 在提示框中显示返回消息
			$("#hintBody").text(data.msg);
			// 显示提示框
			$("#hintModal").modal("show");
			// 2秒后，自动隐藏提示框
			setTimeout(function(){
				$("#hintModal").modal("hide");
				// 成功时刷新页面
				if(data.code == 0){
					window.location.reload();
				}
			}, 2000);
		}

	);


}