<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link href="../res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="../res/fckeditor/fckeditor.js"></script>
<script src="../res/common/js/jquery.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="../res/common/js/lecheng.js" type="text/javascript"></script>
<script src="../res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="../res/css/style.css" />

<!-- 添加表单验证 -->
<script type="text/javascript">

 	//文档就绪事件

	$(function(){
		//下拉框change事件
		$("#channel").change(function(){
			
			//清空第二个下拉框
			$("#channel2").empty();
			//调用jQuery的提交方法
			//ajax的异步方法
			$.post(
				//提交的url	
				"getchan.do",
				//提交的数据 json
				{"pid":this.value},
				//成功后回调方法
				function(data){
					//装载数据
					if(data!=null){
						$(data).each(
						function () {
							//装到下拉框
							$("#channel2").append("<option value="+this.id+">"+this.cname+"</option>");
						});
					}
				},
				//返回类型
				"json"	
			);
			
		});
	});

</script>

<title>文章修改</title>
<style type="">
.h2_ch a:hover,.h2_ch a.here {
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
	background: url('../res/lecheng/img/admin/bg_ch.gif') repeat-x scroll 0%
		0% transparent;
}

a {
	color: #06C;
	text-decoration: none;
}
</style>

</head>
<body>
<div>
	<img src="images/logo4.png" />
</div>
<div class="box-positon">
	<div class="rpos">当前位置: 文章管理 - 修改</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='list.do';"
			value="返回列表" class="return-button" />
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float: right">
		<form id="jvForm" action="update.do" method="post">
		
		<input type="hidden" name="id" value="${ARTICLE.id }"/>
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody id="tab_1">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired"></span> <span class="pn-frequired">${msg
								}</span></td>
					</tr>
					
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 标题:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="title" id="title" maxlength="100" size="50"
							value="${ARTICLE.title }" /></td>
					</tr>
					
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 内容:</td>
						<td width="80%" class="pn-fcontent"><textarea rows="15"
								cols="120" id="content" name="content">${ARTICLE.content }</textarea></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">作者:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="creator" id="creator" maxlength="100" size="50"
							value="${ARTICLE.creator }" /></td>
					</tr>
					
					<!-- 栏目 -->
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">所属栏目:</td>
						<td width="80%" class="pn-fcontent">
						<select id="dep1" name="dep1" >
							<c:forEach items="${DLIST}" var="dep1">
								<!-- 该用户所在部门的上级部门id等于一级部门id 就选择 -->
								<c:if test="${USER.dept.pid==dep1.id }">
									<option value="${dep1.id}" selected="selected">${dep1.name}</option>
								</c:if>
								<!-- 否则不选中 -->
								<c:if test="${USER.dept.pid!=dep1.id }">
									<option value="${dep1.id}" >${dep1.name}</option>
								</c:if>
							</c:forEach>
						</select>
						
						<select id="dep2" name="dept.id" >
							<c:forEach items="${DLIST2}" var="dep2">
								<!-- 该用户所在部门的上级部门id等于一级部门id 就选择 -->
								<c:if test="${USER.dept.id==dep2.id }">
									<option value="${dep2.id}" selected="selected">${dep2.name}</option>
								</c:if>
								<!-- 否则不选中 -->
								<c:if test="${USER.dept.id!=dep2.id }">
									<option value="${dep2.id}" >${dep2.name}</option>
								</c:if>
							</c:forEach>
						</select>
						
						<select name="channel" id="channel">
							<c:forEach items="${CLIST}" var="tchannel">
								<!-- 显示所属栏目 -->
								<c:if test="${ARTICLE.channel==tchannel.id }">
									<option value="${channel.id}" selected="selected">${channel.cname}</option>
								</c:if>
								<!-- 显示其他栏目 -->
								<c:if test="${ARTICLE.tchannel!=tchannel.id }">
									<option value="${tchannel.id}">${tchannel.cname}</option>
								</c:if>
							</c:forEach>
						</select>
						</td>
					</tr>
					
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否推荐:</td>
						<td width="80%" class="pn-fcontent"><c:if
								test="${ARTICLE.isremod==1 }">
								<input type="radio" name="isremod" value="1" checked="checked" />是 
							<input type="radio" name="isremod" value="-1" />否						
						</c:if> 
						<c:if test="${ARTICLE.isremod==-1 }">
								<input type="radio" name="isremod" value="1" />是
							<input type="radio" name="isremod" value="-1" checked="checked" />否
						
						</c:if></td>


					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否热点:</td>
						<td width="80%" class="pn-fcontent"><c:if
								test="${ARTICLE.ishot==1 }">
								<input type="radio" name="ishot" value="1" checked="checked" />是 
							<input type="radio" name="ishot" value="-1" />否
						</c:if> 
						<c:if test="${ARTICLE.ishot==-1 }">
								<input type="radio" name="ishot" value="1" />是 
							<input type="radio" name="ishot" value="-1" checked="checked" />否
						</c:if></td>
					</tr>

				</tbody>
			
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="submit"
							class="submit" value="提交" /> &nbsp; <input type="reset"
							class="reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>