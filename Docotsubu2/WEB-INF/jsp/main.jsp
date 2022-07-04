<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    
 <%@page import="model.User,model.Mutter,java.util.List" %>
 
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>つぶやき</title>
		</head>
		<body>
			<h1>メイン画面</h1>
			<p><c:out value="${loginUser.name}" />さん がログイン中です。</p>
			<a href="/DocoTsubu2/Logout">ログアウト</a>
			
			<p><a href="/DocoTsubu2/Main">更新</a></p>
			<form action="/DocoTsubu2/Main" method="post">
			<input type="text" name="text">
			<input type="submit" value="つぶやく">
			</form>
			<%-- --%>
			<form action="/DocoTsubu2/Main" method="get">
			<input type="text" name="search" value="${search}">  
			<input type="submit" value="検索">
			</form>
		
			<%--リクエストスコープからエラーメッセージを取得できたら表示 --%>
			<c:if test="${not empty errMsg}">
			<p style="color:red;"><strong>${errMsg}</strong></p>
			</c:if>
			
			<%--検索エラーメッセージ 検索しているのに、リストに何もなければエラーを表示--%>
			<c:if test="${not empty search && mutterList.isEmpty()}">
				<p style="color:red;"><strong><c:out value="${search}" /> に関するつぶやきはありません</strong></p>
			</c:if>	
				
			<%--JSTLを利用してつぶやき一覧を取得 --%>
				<c:forEach var="mutter" items="${requestScope.mutterList}">
						<p><c:out value="${mutter.userName}：${mutter.text}" /></p>
					</c:forEach>
			
		</body>
	</html>