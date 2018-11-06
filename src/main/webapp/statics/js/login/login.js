$(function () {

    //初始化
    var layer;
    layui.use('layer', function () {
        layer = layui.layer;
    });
    $("#loginButton").click(function () {
        login();
    });

    //注册回车登录事件
    $(document).keyup(function(event){
        if(event.keyCode ==13){
            $("#loginButton").click();
        }
    });

});

function login() {
    layer.msg('登录中...');
    //检测数据
    if (!check()) {
        return;
    }
    // 加密密码
    if (!rsa()) {
        return;
    }

    $.ajax({
            url: getRootPath() + "/subLogin",
            type: 'post',
            data: $("#loginForm").serialize(),
            success: function (result) {
                if (result.ret) {
                    layer.msg(result.message, {icon: 6, shade: 0.00001, shadeClose: true, closeBtn: 1},function () {
                         window.location.href = getRootPath() + '/admin';
                    });
                } else {
                    layer.msg(result.message, {icon: 2, shade: 0.00001, shadeClose: true, closeBtn: 1});
                    //登录失败更新验证码
                    newVcoed();
                }
            }
        }
    );

}

/**
 * 更新验证码
 */
function newVcoed() {
    $(".vcodeImg").attr("src", getRootPath() + "/vcode?time=" + new Date().getTime());
}
/**
 * 验证登录信息
 */
function check() {
    //获取字段
    var username = $.trim($("#username").val());
    var password = $.trim($("#password").val());
    var verifyCode = $.trim($("#verifyCode").val());

    if (username == "") {
        $("#username").val('');
        layer.msg("请输入用户名!", {icon: 5, shade: 0.00001, shadeClose: true, closeBtn: 1}, function () {
            $("#username").focus();
        });
        return false;
    } else if (password == "") {
        $("#password").val('');
        layer.msg("请输入密码!", {icon: 5, shade: 0.00001, shadeClose: true, closeBtn: 1}, function () {
            $("#password").focus();
        });
        return false;
    } else if (verifyCode == "") {
        $("#verifyCode").val('');
        layer.msg("请输入验证码!", {icon: 5, shade: 0.00001, shadeClose: true, closeBtn: 1}, function () {
            $("#verifyCode").focus();
        });
        return false;
    }
    return true;


}

/**
 * 使用rsa算法加密密码
 */
function rsa() {
    //获取用户输入的密码
    var password = $("#password").val();
    //获取rsa公钥
    var publicKey = "";
    $.ajax({
        url: getRootPath() + "/getPubKey"
        , type: "post"
        , async: false//使用同步请求
        //, data: $("#loginForm").serialize()
        , success: function (data) {
            if (data.ret) {
                publicKey = data.object;
            } else {//请求失败
                layer.msg("安全数据请求失败!无法建立安全数据通道!", {icon: 2, shade: 0.00001, shadeClose: true, closeBtn: 1});
                return false;
            }
        }
        , fail: function (data) {
            layer.msg("发生未知错误!", {icon: 2, shade: 0.00001, shadeClose: true, closeBtn: 1});
            return false;
        }
    });

    //验证公钥
    if ($.trim(publicKey) == "") {
        layer.msg("安全数据异常!无法建立安全数据通道!", {icon: 2, shade: 0.00001, shadeClose: true, closeBtn: 1});
        return false;
    }
    //创建加密算法对象
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(publicKey);
    password = encrypt.encrypt(password);
    //将加密后的密码设置到隐藏的密码框中
    $("#realPass").val(password);
    return true;
}
