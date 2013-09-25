<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ryan.manage.entity.DishEntity" %>

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
<script language="javascript">
    function check(){
        if(namecheck()&&seriescheck()&&pricecheck()&&imgcheck()){
            alert('添加餐品成功！');
            return true;
        }
        return false;
    }
    function namecheck(){					
        if(document.frm.dishname.value.length==0){
            document.getElementById("namediv").innerHTML="<font color='red'>菜品名称不能为空！</font>";
            document.frm.dishname.focus();
            return false;
        }
        else{
            document.getElementById("namediv").innerHTML=" ";
        }
        return true;
    }
    function seriescheck(){
        if(document.frm.dishseries.value.length==0){
            document.getElementById("seriesdiv").innerHTML="<font color='red'>菜品类型不能为空！</font>";
            document.frm.dishseries.focus();
            return false;
        }
        else{
            document.getElementById("seriesdiv").innerHTML=" ";
        }
        return true;
    }
    function pricecheck(){
        if(document.frm.dishprice.value.length==0){
            document.getElementById("pricediv").innerHTML="<font color='red'>菜品价格不能为空！</font>";
            document.frm.dishprice.focus();
            return false;
        }
        else{
            document.getElementById("pricediv").innerHTML=" ";
        }
        return true;
    }
    function imgcheck(){
        str = document.frm.dishimg.value;
        var reg1= /^.+\.jpg$/i;
        var reg2 =  /^.+\.gif$/i;
        var reg3 = /^.+\.png$/i;
        if(!reg1.test(str) && !reg2.test(str)&&!reg3.test(str) && str.toString().length!=0){
            document.getElementById("imgdiv").innerHTML="<font color='red'>请选择jpg、gif或png格式的图片！</font>";
            document.frm.dishimg.focus();
            return false;
        }
        else{
            document.getElementById("imgdiv").innerHTML=" ";
        }
        return true;
    }
</script>

<body>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
            <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
                    <tr>
                        <td height="31"><div class="titlebt">添加餐品</div></td>
                    </tr>
                </table></td>
            <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
        </tr>
        <tr>
            <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
            <td valign="top" bgcolor="#F7F8F9">
                <table  width="98%" border="0" align="center">
                    <tr>
                        <td class="left_txt">当前位置：添加餐品</td>
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
                                    <td width="10%" height="55" valign="middle"><img src="images/lminfo.gif" width="54" height="55"></td>
                                    <td width="90%" valign="top"><span class="left_txt2">在这里，您可以进行</span><span class="left_txt3">添加餐品</span><span class="left_txt2">的操作。</span><br>
                                        <span class="left_txt2">请输入完整的</span><span class="left_txt3">餐品名称，餐品类型，餐品价格</span><span class="left_txt2">。 </span></td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                </table>
                <form id="frm" name="frm" style="padding: 0px;margin: 0px;" action="AddDishServlet" method="post" enctype="multipart/form-data">
                    <table  width="98%" cellpadding="0" cellspacing="0" align="center" style="text-align:left; border:solid thin #d7d7d7;">
                        <tr height="30">
                            <td width="30px">&nbsp;</td>
                            <td align="left">
                                <span class="left_bt2">餐品名称：</span>
                                <input type="text" name="dishname" style ="width: 249px"/>
                                &nbsp;
                                <div id="namediv" style="display:inline;color:gray;font-size:12px;">
                                    如：客家小炒
                                </div>
                            </td>
                        </tr>
                        <tr height="30" style="background-color:#f2f2f2;">
                            <td width="30px">&nbsp;</td>
                            <td align="left">
                                <span class="left_bt2">餐品类型：</span>
                                <!--                                <input type="text" name="dishseries" size="22" />-->
                                <select name ="dishseries" style ="width: 249px">
                                    <option value="盖饭">盖饭</option>
                                    <option value="小炒">小炒</option>
                                    <option value="面食">面食</option>
                                    <option value="饮料">饮料</option>
                                </select>
                                &nbsp;
                                <div id="seriesdiv" style="display:inline;color:gray;font-size:12px;">
                                    请选择一种餐品类型
                                </div>
                            </td>
                        </tr>
                        <tr height="30">
                            <td width="30px">&nbsp;</td>
                            <td align="left">
                                <span class="left_bt2">餐品价格：</span>
                                <input type="text" name="dishprice" style ="width: 249px"/>
                                &nbsp;
                                <div id="pricediv" style="display:inline;color:gray;font-size:12px;">
                                    请输入一个数字
                                </div>
                            </td>
                        </tr>
                        <tr height="30" style="background-color:#f2f2f2;">
                            <td width="30px">&nbsp;</td>
                            <td align="left">
                                <span class="left_bt2">餐品图片：</span>
                                <input type="file" name="dishimg" size="14"/>
                                &nbsp;
                                <div id="imgdiv" style="display:inline;color:gray;font-size:12px;">
                                    请选择一张格式为jpg,gif或png的图片，若不设置图片请放空此项
                                </div>
                            </td>
                        </tr>
                        <tr height="30" style="background-color:#CCCCFF;">
                            <td width="30px">&nbsp;</td>
                            <td align ="left">
                                <input name="adddish" type="submit" value="新增" onclick="return check();"/>
                            </td>
                        </tr>
                    </table>
                </form>
                <!--<table width="98%" border="0" cellpadding="0" cellspacing="0"><tr>&nbsp;</tr><tr><td width="100%" align="left"><a href="javascript:" onclick="document.addFrm.submit();" >新增</a></td></tr></table>-->
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
