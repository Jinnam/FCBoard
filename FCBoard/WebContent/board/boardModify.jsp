<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>BOARD MODIFY FORM</title>
</head>
<body>
<h1>BOARD MODIFY</h1>    
    <form action="<c:url value='/board/boardModify'/>" method="post">
        <div>boardNo</div>
        <div><input name="boardNo" value="${board.boardNo}" type="text" readonly="readonly"/></div>
        <div>비밀번호확인: </div>
        <div><input name="boardPw" id="boardPw" type="password"/></div>
        <div>boardTitle : </div>
        <div><input name="boardTitle" value="${board.boardTitle}" id="boardTitle" type="text"/></div>
        <div>boardContent : </div>
        <div><textarea name="boardContent" id="boardContent" rows="5" cols="50">${board.boardContent}</textarea></div>
        <div>
            <input type="submit" value="글수정"/>
            <input type="reset" value="초기화"/>
        </div>
    </form>
</body>
</html>