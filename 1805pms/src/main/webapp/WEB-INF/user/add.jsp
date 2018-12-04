<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


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


<title>user-add</title>
<!-- 引入jqyery函数库 -->

<!-- 引入时间控件 -->
<script type="text/javascript" language="javascript" src="../js/DatePicker/WdatePicker.js"></script>

<!-- 添加表单验证 -->
<script type="text/javascript">

	//文件上传
	function upload(){
		//
		//
		//alert("1111");
		var args={
			//
			url:$("#path").val()+"/upload/common.do",
			//返回类型
			dataType:"text",
			//提交方式
			type:"post",
			success:function(result){
				alert($("#path").val())
				//设置图片属性
				$("#img").attr("src",$("#path").val()+"/upload/"+result);
				//将路径设置到隐藏域中
				$("#pic").val(result);
			}
		}
		//
		$("#jvForm").ajaxSubmit(args);
	}














	//文档就绪事件
	$(function(){
		//下拉框change事件
		$("#dep1").change(function(){
			
			//清空第二个下拉框
			$("#dep2").empty();
			//调用jQuery的提交方法
			//ajax的异步方法
			$.post(
				//提交的url	
				"getdep.do",
				//提交的数据 json
				{"pid":this.value},
				//成功后回调方法
				function(data){
					//装载数据
					if(data!=null){
						$(data).each(
						function () {
							//装到下拉框
							$("#dep2").append("<option value="+this.id+">"+this.name+"</option>");
						});
					}
				},
				//返回类型
				"json"	
			);
			
		});
	});

/* 
	//用户名：数字+字母，结束之前不能全部都是数字，6-16
	var CHKLOGINNAME = "^(?![0-9]+$)[a-zA-Z0-9]{6,16}$";
	//密码:数字+字母，结束之前不能全部都是数字和字母，6-16
	var CHKPASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,16}$";
	//出生日期     yyyy-MM-dd  月份1-12     日期1-31
	var CHKDATE = "^[0-9]{4}-0?[1-9]|1[0-2]-0?[1-9]|[1-2][0-9]|3[0-1]$";
	//邮箱xxxxxx@xxx.com,可以包含_      企业邮箱qwe@huewei.com.cn
	var CHKEMAIL = "^[a-zA-Z0-9_]+@[a-z0-9]{2,5}(\\.[a-z]{2,3}){1,2}$";
	//真实姓名
	var CHKREALNAME = "^[\u4e00-\u9fa5]{2,}$";


	//验证用户名
	function chkloginname() {
		//获取用户名
		var loginname = $("#loginname").val();
		//定义匹配用户名的正则表达式
		var reg = new RegExp(CHKLOGINNAME);
		if (reg.test(loginname)) {//表示输入正确
			if (chkExistLoginname(loginname)) {
				return true;
			}else{
				return false;
			}
		} else {//输入失败
			$("#resultName").html("用户名必须包含数字和字母，并且不能低于六位");
			$("#resultName").css("color", "red");
			//清空文本框
			$("#loginname").val("");
			//重新聚焦
			$("#loginname").focus();
			return false;
		}
	}
	
 
	
	
	//验证密码
	function chkpassword() {
		//获取密码
		var password = $("#password").val();
		//定义匹配用户名的正则表达式
		var reg = new RegExp(CHKPASSWORD);
		if (reg.test(password)) {//表示输入正确
			$("#resultPwd").html("✔");
			$("#resultPwd").css("color", "green");
			return true;
		} else {
			$("#resultPwd").html("密码必须包含数字和字母，并且不能低于六位");
			$("#resultPwd").css("color", "red");
			//清空文本框
			$("#password").val("");
			//重新聚焦
			$("#password").focus();
			return false;
		}
	}

	//验证两次密码是否一致
	function chkRePwd() {
		//获取密码
		var repwd = $("#repwd").val();
		var password = $("#password").val();
		//获取2次密码是否输入成功的元素对象
		if (repwd == password) {
			$("#resultRepwd").html("✔");
			$("#resultRepwd").css("color", "green");
			return true;
		} else {
			$("#resultRepwd").html("两次密码不一致");
			$("#resultRepwd").css("color", "red");
			//清空文本框
			$("#repwd").val("");
			//重新聚焦
			$("#repwd").focus();
			return false;
		}
	}

	//验证真实姓名
	function chkrealname() {
		//获取真实姓名
		var realname = $("#realname").val();
		//定义匹配真实姓名的正则表达式
		var reg = new RegExp(CHKREALNAME);
		if (reg.test(realname)) {//表示输入正确
			$("#resultRename").html("✔");
			$("#resultRename").css("color", "green");
			return true;
		} else {//输入失败
			$("#resultRename").html("真实姓名必须为2-4个汉字");
			$("#resultRename").css("color", "red");
			//清空文本框
			$("#realname").val("");
			//重新聚焦
			$("#realname").focus();
			return false;
		}
	}

	//验证邮箱
	function chkemail() {
		//获取邮箱
		var email = $("#email").val();
		//定义匹配邮箱的正则表达式
		var reg = new RegExp(CHKEMAIL);
		if (reg.test(email)) {//表示输入正确
			if (chkExistEmail(email)) {
				return true;
			}else{
				return false;
			}
		} else {//输入失败
			$("#resultEmail").html("邮箱格式不正确。例：xxxxxx@xxx.com");
			$("#resultEmail").css("color", "red");
			//清空文本框
			$("#email").val("");
			//重新聚焦
			$("#email").focus();
			return false;
		}
	}

	
	//验证所有
	function chkAll() {
		return chkloginname() && chkpassword() && chkRePwd() && chkRePwd()
				&& chkRePwd();
	}
 */	
	
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>user-add</title>
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
<!--  -->
<input type="hidden" id="path" value="${pageContext.request.contextPath }" >

	<div class="box-positon">
		<div class="rpos">当前位置: 用户管理 - 添加</div>
		<form class="ropt">
			<input type="submit" onclick="this.form.action='list.do';"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box" style="float: right">
		<form name="fm" id="jvForm" action="add.do" method="post" enctype="multipart/form-data">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired"></span> <span class="pn-frequired">${msg}</span>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 用户名:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							value="" class="required" name="loginname" id="loginname"
							maxlength="10" onblur="chkloginname()" /> <span id="resultName"></span>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 密码:</td>
						<td width="80%" class="pn-fcontent"><input type="password"
							value="" class="required" name="password" id="password"
							maxlength="10" onblur="chkpassword()" /> <span id="resultPwd"></span>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 确认密码:</td>
						<td width="80%" class="pn-fcontent"><input type="password"
							value="" class="required" name="repwd" id="repwd" maxlength="10"
							onblur="chkRePwd()" /> <span id="resultRepwd"></span></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 真实姓名:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							value="" class="required" name="realname" id="realname"
							maxlength="10" onblur="chkrealname()" /> <span id="resultRename"></span>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">性别:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							name="sex" value="男" checked="checked" />男 <input type="radio"
							name="sex" value="女" />女</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">出生日期:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							name="birthday" maxlength="80" class="Wdate"
							onclick="WdatePicker()" /></td>
					</tr>

					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">部门:</td>
						<td width="80%" class="pn-fcontent">
						<select id="dep1" name="dep1">
							<c:forEach items="${DLIST}" var="dep1">
								<option value="${dep1.id}" >${dep1.name}</option>
							</c:forEach>
						</select>
						<select id="dep2" name="dept.id">
							<c:forEach items="${DLIST2}" var="dep2">
								<option value="${dep2.id}" >${dep2.name}</option>
							</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> email:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							value="" class="required" name="email" id="email" maxlength="10"
							onblur="chkemail()" /> <span id="resultEmail"></span></td>
					</tr>

					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">
						头像:</td><td width="80%" class="pn-fcontent">
						<input type="file" name="file" onchange="upload()">
						<img src="" id="img" width="150px" height="150px" >
						
						<input type="hidden" name="pic" id="pic"> 
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