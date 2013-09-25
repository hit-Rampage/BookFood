<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ryan.manage.entity.UserEntity" %>

<link href="images/skin.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    <!--
    body {
        margin-left: 0px;
        margin-top: 0px;
        margin-right: 0px;
        margin-bottom: 0px;
        background-color: #EEF2FB;
    }
    -->
</style>
<body>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
            <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
                    <tr>
                        <td height="31"><div class="titlebt">用户管理</div></td>
                    </tr>
                </table></td>
            <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
        </tr>
        <tr>
            <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
            <td valign="top" bgcolor="#F7F8F9">
                <table  width="98%" border="0" align="center">
                    <tr>
                        <td class="left_txt">当前位置：用户管理</td>
                    </tr>
                    <tr>
                        <td height="20"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                <tr>
                                    <td></td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td><table width="100%" height="55" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td width="10%" height="55" valign="middle"><img src="images/user-info.gif" width="54" height="55"></td>
                                    <td width="90%" valign="top"><span class="left_txt2">在这里，您可以查看</span><span class="left_txt3">注册用户信息</span><span class="left_txt2">。</span><br>
                                        <span class="left_txt2">并可以进行</span><span class="left_txt3">禁用/解禁用户，删除已禁用户</span><span class="left_txt2">的操作。 </span></td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                </table>
                <table  width="98%" cellpadding="0" cellspacing="0" align="center" style="text-align:center; border:solid thin #d7d7d7; table-layout: fixed;">
                    <tr height="31"  style="background-color:#CCCCFF;">
                        <td class="left_bt2">
                            用户ID
                        </td>
                        <td class="left_bt2">
                            用户名
                        </td>
                        <td  class="left_bt2">
                            用户密码
                        </td>
                        <td  class="left_bt2">
                            用户状态
                        </td>
                        <td  class="left_bt2">
                            操作
                        </td>
                    </tr>
                    <%
                        List list = (List) request.getAttribute("userlist");
                        int lineFlag = 0;
                        for (int i = 0; i < list.size(); i++) {
                            UserEntity user = (UserEntity) list.get(i);
                            if (user.getUserState().equals("被禁")) {
                                continue;
                            }
                            if ((lineFlag++) % 2 == 0) {
                    %>
                    <tr height="30">	
                        <%                            } else {
                        %>
                    <tr height="30" style="background-color:#f2f2f2;">
                        <%                            }
                        %>
                        <td class="left_txt2">
                            <%=user.getUserId()%>
                        </td>
                        <td class="left_txt2">
                            <%=user.getUserName()%>
                        </td>
                        <td class="left_txt2">
                            <%=user.getPassword()%>
                        </td>
                        <td class="left_txt2">
                            <%=user.getUserState()%>
                        </td>
                        <td>
                            <%String tmp = "f" + user.getUserId();%>
                            <a href="ExStateServlet?id=<%=tmp%>" method="post" onclick="return confirm('您确认要禁用该用户？')" >禁用</a>
                        </td>
                    </tr>
                    <%
                        }
                        for (int i = 0; i < list.size(); i++) {
                            UserEntity user = (UserEntity) list.get(i);
                            if (user.getUserState().equals("正常")) {
                                continue;
                            }
                            if ((lineFlag++) % 2 == 0) {
                    %>
                    <tr height="30">	
                        <%                            } else {
                        %>
                    <tr height="30" style="background-color:#f2f2f2;">
                        <%                            }
                        %>
                        <td class="left_txt2">
                            <%=user.getUserId()%>
                        </td>
                        <td class="left_txt2">
                            <%=user.getUserName()%>
                        </td>
                        <td class="left_txt2">
                            <%=user.getPassword()%>
                        </td>
                        <td class="left_txt2">
                            <%=user.getUserState()%>
                        </td>
                        <td>
                            <%String tmp = "g" + user.getUserId();%>
                            <a href="ExStateServlet?id=<%=tmp%>" method="post" onclick="return confirm('您确认要解禁该用户？')" >解禁</a> /
                            <%tmp = "h" + user.getUserId();%>
                            <a href="ExStateServlet?id=<%=tmp%>" method="post" onclick="return confirm('删除将不可恢复，且该用户留言也将被删除。您确认要删除该用户？')" > 删除</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                <tr>
                                    <td></td>
                                </tr>
                            </table></td>
                    </tr>

                    <tr>
                        <td width="2%">&nbsp;</td>
                        <td width="51%" class="left_txt"><img src="images/icon-mail2.gif" width="16" height="11"> 官方服务邮箱：yangchenhao1991@gmail.com<br>
                            <img src="images/icon-phone.gif" width="17" height="14"> 官方电话：15145106805</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </td>
            <td background="images/mail_rightbg.gif">&nbsp;</td>
        </tr>
        <tr>
            <td valign="bottom" background="images/mail_leftbg.gif"><img src="images/buttom_left2.gif" width="17" height="17" /></td>
            <td background="images/buttom_bgs.gif"><img src="images/buttom_bgs.gif" width="17" height="17"></td>
            <td valign="bottom" background="images/mail_rightbg.gif"><img src="images/buttom_right2.gif" width="16" height="17" /></td>
        </tr>
    </table>
</body>
