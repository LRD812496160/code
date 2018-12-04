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

/* 	//文档就绪事件
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
 */
</script>









<title>文章新增</title>
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
		<div class="rpos">当前位置: 文章管理 - 添加</div>
		<form class="ropt">
			<input type="submit" onclick="this.form.action='list.do';"
				value="返回列表" class="return-button" />
		</form>
	</div>

	<div class="body-box" style="float: right">
		<form id="jvForm" action="add.do" method="post">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody >
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>
						<span class="pn-frequired">${msg }</span>
					</td>
				</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 标题:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="title" id="title" maxlength="100" size="50" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 内容:</td>
						<td width="80%" class="pn-fcontent"><textarea rows="15"
								cols="120" id="content" name="content"></textarea></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">作者:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="creator" id="creator" maxlength="100" size="50" /></td>
					</tr>
					
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">所属栏目:</td>
						<td width="80%" class="pn-fcontent">
						
						<select id="channel" name="channel">
							<c:forEach items="${CLIST}" var="channel">
								<option value="${channel.id}">${channel.cname}</option>
							</c:forEach>
						</select>	
						</td>
					</tr>
					
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否推荐:</td>
						<td width="80%" class="pn-fcontent">
						<input type="radio"name="isremod" value="1" checked="checked" />是 
						<input type="radio" name="isremod" value="-1" />否</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否热点:</td>
						<td width="80%" class="pn-fcontent">
						      <input type="radio" name="ishot" value="1" checked="checked" />是
							 <input type="radio"name="ishot" value="-1" />否
					   </td>
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