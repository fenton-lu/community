$(function(){
    $("#topBtn").click(setTop);
    $("#wonderfulBtn").click(setWonderful);
    $("#deleteBtn").click(setDelete);
});

function like(btn, entityType, entityId, entityUserId, postId){
    // 异步请求
    $.post(
        CONTEXT_PATH+"/like",
        {"entityType":entityType, "entityId": entityId, "entityUserId": entityUserId, "postId": postId},
        function(data){
            data = $.parseJSON(data);
            if(data.code == 0){ //成功
                $(btn).children("i").text(data.likeCount);
                $(btn).children("b").text(data.likeStatus==1?'已赞':'赞');
            }else{ // 失败
                alert(data.msg);
            }
        }
    );

}

// 置顶
function setTop() {
    $.post(
        CONTEXT_PATH + "/discuss/top",
        {"id":$("#postId").val()},
        function(data) {
            data = $.parseJSON(data);
            if(data.code == 0) { // 置顶后设置按钮不可用
                $("#topBtn").attr("disabled", "disabled");
            } else {
                alert(data.msg);
            }
        }
    );
}

// 加精
function setWonderful() {
    $.post(
        CONTEXT_PATH + "/discuss/wonderful",
        {"id":$("#postId").val()},
        function(data) {
            data = $.parseJSON(data);
            if(data.code == 0) {
                $("#wonderfulBtn").attr("disabled", "disabled");
            } else {
                alert(data.msg);
            }
        }
    );
}

// 删除
function setDelete() {
    $.post(
        CONTEXT_PATH + "/discuss/delete",
        {"id":$("#postId").val()},
        function(data) {
            data = $.parseJSON(data);
            if(data.code == 0) {
                location.href = CONTEXT_PATH + "/index"; // 跳转到首页
            } else {
                alert(data.msg);
            }
        }
    );
}