
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
    <head>
        <base target="main">
        <link href="images/skin.css" rel="stylesheet" type="text/css">
        <script language ="javascript">
            function logout(){
                if(confirm("您确认要退出订餐管理吗？")){
                    top.location = "login.jsp";
                }
                return false;
            }
        </script>
    </head>
    <body leftmargin="0" topmargin="0">
        <table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
            <tr>
                <td width="61%" height="64"><img src="images/logo.png" width="262" height="64"></td>
                <td width="39%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="74%" height="38" class="admin_txt"><marquee direction="left">管理员<b><%=session.getAttribute("adminname")%></b> 您好,感谢登陆使用！</marquee></td>
                            <td width="22%"><a onclick="logout();"><img src="images/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
                            <td width="4%">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="19" colspan="3">&nbsp;</td>
                        </tr>
                    </table></td>
            </tr>
        </table>
    </body>
</html>

