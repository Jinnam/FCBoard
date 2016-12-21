<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
</head>
<body>
<h1>BOARD VIEW</h1>
    <div>board_no :</div>
    <div>${board.boardNo}</div>
    <div>board_title :</div>
    <div>${board.boardTitle}</div>
    <div>board_content :</div>
    <div>${board.boardContent}</div>
    <div>board_user :</div>
    <div>${board.boardUser}</div>
    <div>board_date :</div>
    <div>${board.boardDate}</div>
    <div>
        <a href="<c:url value='/board/boardModify?boardNo=${board.boardNo}'/>">수정</a>
        <a href="<c:url value='/board/boardRemove?boardNo=${board.boardNo}'/>">삭제</a>
    </div>
</body>
</html>
