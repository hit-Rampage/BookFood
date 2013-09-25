<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ryan.manage.entity.DeliverEntity" %>

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
                        <td height="31"><div class="titlebt">历史订单</div></td>
                    </tr>
                </table></td>
            <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
        </tr>
        <tr>
            <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
            <td valign="top" bgcolor="#F7F8F9">
                <table  width="98%" border="0" align="center">
                    <tr>
                        <td class="left_txt">当前位置：历史订单</td>
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
                                    <td width="10%" height="55" valign="middle"><img src="images/DefaultDocs.gif" width="54" height="55"></td>
                                    <td width="90%" valign="top"><span class="left_txt2">在这里，您可以查看</span><span class="left_txt3">历史订单</span><span class="left_txt2">。</span><br>
                                        <span class="left_txt2">并可以进行</span><span class="left_txt3">删除订单</span><span class="left_txt2">的操作。 </span></td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                </table>
                <table  width="98%" cellpadding="0" cellspacing="0" align="center" style="text-align:center; border:solid thin #d7d7d7;">
                    <tr height="31"  style="background-color:#CCCCFF;">
                        <td class="left_bt2">
                            订单号码
                        </td>
                        <td class="left_bt2">
                            用户姓名
                        </td>
                        <td  class="left_bt2">
                            用户地址
                        </td>
                        <td  class="left_bt2">
                            电话号码
                        </td>
                        <td  class="left_bt2">
                            订单内容
                        </td>
                        <td  class="left_bt2">
                            订单状态
                        </td>
                        <td  class="left_bt2">
                            订单时间
                        </td>
                        <td  class="left_bt2">
                            订单总价
                        </td>
                        <td  class="left_bt2">
                            操作
                        </td>
                    </tr>
                    <%
                        List list = (List) request.getAttribute("deliverlist");
                        for (int i = 0; i < list.size(); i++) {
                            DeliverEntity deliver = (DeliverEntity) list.get(i);
                            if (i % 2 == 0) {
                    %>
                    <tr height="30">	
                        <%                            } else {
                        %>
                    <tr height="30" style="background-color:#f2f2f2;">
                        <%                            }
                        %>
                        <td class="left_txt2">
                            <%=deliver.getDeliverId()%>
                        </td>
                        <td class="left_txt2">
                            <%=deliver.getUserName()%>
                        </td>
                        <td class="left_txt2">
                            <%=deliver.getAddress()%>
                        </td>
                        <td class="left_txt2">
                            <%=deliver.getTelephone()%>
                        </td>
                        <td class="left_txt2">
                            <%=deliver.getOrderItem()%>
                        </td>
                        <td class="left_txt2">
                            <%=deliver.getOrderState()%>
                        </td>
                        <td class="left_txt2">
                            <%=deliver.getOrderTime()%>
                        </td>
                        <td class="left_txt2">
                            <%=deliver.getOrderPrice()%>
                        </td>
                        <td>
                            <%String tmp = "e" + deliver.getDeliverId();%>
                            <a href="ExStateServlet?id=<%=tmp%>" method="post" onclick="return confirm('删除将不可恢复，您确认要删除该订单？')" >删除</a>
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
