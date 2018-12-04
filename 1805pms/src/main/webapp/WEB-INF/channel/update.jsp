<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link href="res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- 引时间控件 -->
<script language="javascript" type="text/javascript" src="js/DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="res/fckeditor/fckeditor.js"></script>
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/lecheng.js" type="text/javascript"></script>
<script src="res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="../res/css/style.css" />
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>






<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>栏目修改</title>
<style type="">
.h2_ch a:hover, .h2_ch a.here {
    color: #FFF;
    font-weight: bold;
    background-position: 0px -32px;
}
.h2_ch a {
    float: left;
    height: 32px;
    margin-right: -1px;
    padding: 0px 16px;
    line-height: 32px;
    font-size: 14px;
    font-weight: normal;
    border: 1px solid #C5C5C5;
    background: url('../res/lecheng/img/admin/bg_ch.gif') repeat-x scroll 0% 0% transparent;
}
a {
    color: #06C;
    text-decoration: none;
}
</style>

</head>
<body>
	<div>
		<img src="images/logo4.png"/>	
	</div>
<div class="box-positon">
	<div class="rpos">当前位置: 栏目管理 - 修改</div>
	<form class="ropt">
	    
		<input type="submit" onclick="this.form.action='channellist.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float:right">
	<form id="jvForm" action="channelupdate.do?id=${channel.id }" method="post">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
			     <tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
					
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						栏目名称:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="cname" maxlength="100" size="50" value=${channel.cname } />
					</td>
				</tr>
				<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">上级栏目:</td>
						<td width="80%" class="pn-fcontent">
						     <select name="pid">
							 <c:forEach items="${chnames}" var="name">
								<!-- 显示所属栏目 -->
								<c:if test="${channel.pid==name.id }">
									<option value="${name.id}" selected="selected">${name.ccname}</option>
								</c:if>
								<!-- 显示其他栏目 -->
								<c:if test="${channel.pid!=name.id }">
									<option value="${name.id}">${name.ccname}</option>
								</c:if>	
							  </c:forEach>
					         </select>
						</td>
					</tr>
				
				<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否推荐:</td>
						<td width="80%" class="pn-fcontent">
						<c:if test="${channel.lev==1 }">
						    <input type="radio"	name="lev" value="1" checked="checked"/>1 
							<input type="radio" name="lev" value="2" />2					
						</c:if>
						 <c:if test="${channel.lev==2 }">
						    <input type="radio"	name="lev" value="1" />1 
							<input type="radio" name="lev" value="2" checked="checked"/>2
						
						</c:if>   
					   </td>
						
						
				</tr>
				<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否叶子:</td>
						<td width="80%" class="pn-fcontent">
						<c:if test="${channel.lev==1 }">
						    <input type="radio"	name="isleaf" value="1" checked="checked"/>是 
							<input type="radio" name="isleaf" value="0" />不是					
						</c:if>
						 <c:if test="${channel.lev==2 }">
						    <input type="radio"	name="isleaf" value="1" />是
							<input type="radio" name="isleaf" value="0" checked="checked"/>不是
						
						</c:if>   
					   </td>
						
						
					</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						
						顺序:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="sort" maxlength="100" size="20" value=${channel.sort } />
					</td>
				</tr>

				
			</tbody>
			<tbody id="tab_2" style="display: none">
				<tr>
					<td >
						<textarea rows="10" cols="10" id="productdesc" name="description"></textarea>
					</td>
				</tr>
			</tbody>
			<tbody id="tab_3" style="display: none">
				<tr>
					<td >
						<textarea rows="15" cols="136" id="productList" name="packageList"></textarea>
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>