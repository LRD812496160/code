<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="images/skin.css" rel="stylesheet" type="text/css" />


</head>
<body leftmargin="0" topmargin="0">

	<%
		Date date = new Date();
		pageContext.setAttribute("date", date);
	%>


	<table width="100%" height="64" border="0" cellpadding="0"
		cellspacing="0" class="admin_topbg">
		<tr>
			<td width="61%" height="64" valign="top">
				<img src="images/logo4.png" width="176" height="54">
			</td>
			<td width="39%" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="54%" height="38" class="admin_txt">
							<b>${userBean.realname}</b>
							&nbsp;&nbsp;&nbsp;&nbsp;您好,今天是
							<fmt:formatDate value="<%=new Date()%>"
								pattern="yyyy-MM-dd		HH:mm:ss" />
							&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="<%=new Date()%>"
								pattern="E" />&nbsp;&nbsp;&nbsp;&nbsp;感谢登录使用！
						</td>
						<td width="42%">
							<img src="images/out.gif" alt="安全退出" width="46" height="20"
								border="0" onclick="javascript:window.history.back()">
						</td>
						<td width="4%">&nbsp;</td>
					</tr>
					<tr>
						<td height="19" colspan="3">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>