<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import= "com.ryan.manage.servlet.*"%>
<html>
    <head>
        <title>登陆订餐管理系统</title>
        <style type="text/css">
            <!--
            body {
                margin-left: 0px;
                margin-top: 0px;
                margin-right: 0px;
                margin-bottom: 0px;
                background-color: #1D3647;
            }
            -->
        </style>
        <link href="images/skin.css" rel="stylesheet" type="text/css">
        <script type="text/javascript">
            function admincheck(){
                if(document.myform.adminname.value.length==0){
                    alert("请输入登录名");
                    return false;
                }
                if(document.myform.adminpwd.value.length==0){
                    alert("请输入密码");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
                        <tr>
                            <td width="1%" height="21">&nbsp;</td>
                            <td height="42">&nbsp;</td>
                            <td width="17%">&nbsp;</td>
                        </tr>
                    </table></td>
            </tr>
            <tr>
                <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
                        <tr>
                            <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
                                    <tr>
                                        <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td height="149">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td height="80" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                            <tr>
                                                                <td width ="30%">&nbsp;</td>
                                                                <td><img src="images/mylogo.png" width="279" height="68"></td>
                                                            </tr>
                                                        </table></td>
                                                </tr>
                                                <tr>
                                                    <td height="198" align="right" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                            <tr>
                                                                <td width="35%">&nbsp;</td>
                                                                <td height="25" colspan="2" class="left_txt"><p>欢迎使用我工手机订餐管理系统...</p></td>
                                                            </tr>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td width="30%" height="40"><img src="images/icon-demo.gif" width="16" height="16"><a href="login.jsp" class="left_txt3"> 使用说明</a> </td>
                                                                <td width="35%"><img src="images/icon-login-seaver.gif" width="16" height="16"><a href="login.jsp" class="left_txt3"> 在线客服</a></td>
                                                            </tr>
                                                        </table></td>
                                                </tr>
                                            </table></td>
                                    </tr>

                                </table></td>
                            <td width="1%" >&nbsp;</td>
                            <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="4%">&nbsp;</td>
                                        <td width="96%" height="38"><span class="login_txt_bt">登陆订餐管理系统</span></td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                                                <%
                                                    if (session.getAttribute("errormsg") != null) {%>
                                                <td height="25" colspan="2" class="left_txt"><%=session.getAttribute("errormsg")%> </td>
                                                <%    session.removeAttribute("errormsg");
                                                    }
                                                %> 
                                                <tr>
                                                    <td height="164" colspan="2" align="middle"><form name="myform" action="LoginServlet" method="post"  onsubmit="return admincheck()">
                                                            <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                                                                <tr>
                                                                    <td width="13%" height="38" class="top_hui_text"><span class="login_txt">登陆名：&nbsp;&nbsp; </span></td>
                                                                    <td height="38" colspan="2" class="top_hui_text"><input class="editbox4" size="20" name="adminname"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                                                                    <td height="35" colspan="2" class="top_hui_text"><input class="editbox4" type="password" size="20" name="adminpwd">
                                                                        <img src="images/luck.gif" width="19" height="18"> </td>
                                                                </tr>
                                                                <tr>
                                                                    <td height="35" >&nbsp;</td>
                                                                    <td width="20%" height="35" ><input name="Submit" type="submit" class="button" id="Submit" value="登 陆"> </td>
                                                                    <td width="67%">&nbsp;</td>
                                                                </tr>
                                                            </table>
                                                            <br>
                                                        </form></td>
                                                </tr>
                                                <tr>
                                                    <td width="433" height="164" align="right" valign="bottom"><img src="images/login-wel.gif" width="242" height="138"></td>
                                                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                                                </tr>
                                            </table></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table></td>
            </tr>
            <tr>
                <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
                        <tr>
                            <td align="center"><span class="login-buttom-txt">Copyright &copy; 2010-2013 </span></td>
                        </tr>
                    </table></td>
            </tr>
        </table>
    </body>
</html>
