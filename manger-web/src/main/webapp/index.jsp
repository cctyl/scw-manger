<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">

    <%@include file="WEB-INF/include/js.jsp" %>

    <script>
        $(".thumbnail img").css("cursor", "pointer");
        $(".thumbnail img").click(function () {
            window.location.href = "project.jsp";
        });
        $(function () {

            $.ajax(
                {
                    url: "${ctp}/islogin",
                    type: "get",
                    success: function (result) {
                        location.href = "${ctp}/welcome";
                    }
                }
            );

        });
    </script>



</head>
</html>