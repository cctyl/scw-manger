//自定义校验方法
jQuery.validator.addMethod("isUsername", function(value, element) {
    var length = value.length;
    var uPattern  =/^[a-zA-Z0-9_-]{4,10}$/;
    return this.optional(element) || (length == 11 && uPattern.test(value));
}, "用户名为4-10位的字母、数字、下划线等");