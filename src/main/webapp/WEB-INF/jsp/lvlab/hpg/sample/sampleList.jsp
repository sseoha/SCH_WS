<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title.sample" /></title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
</head>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
        <div id="content_pop">
        	<form action="./getSampleList.do" method="post">
        		name : <input type="text" name="name" id="name" value="" /><br /><br />
        		age : <input type="text" name="age" id="age" value="" />
        		<input type="submit" value="조회" />
        	</form>
        	<br />
            <!-- List -->
            <div id="table">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" summary="카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블">
                    <colgroup>
                        <col width="150"/>
                        <col width="80"/>
                        <col width="?"/>
                    </colgroup>
                    <tr>
                        <th align="center"><spring:message code="title.sample.name" /></th>
                        <th align="center"><spring:message code="title.sample.age" /></th>
                        <th align="center"><spring:message code="title.sample.description" /></th>
                    </tr>
                    <c:forEach var="result" items="${resultList}" varStatus="status">
                        <tr>
                            <td align="left" class="listtd"><c:out value="${result.name}"/>&nbsp;</td>
                            <td align="center" class="listtd"><c:out value="${result.age}"/>&nbsp;</td>
                            <td align="center" class="listtd"><c:out value="${result.description}"/>&nbsp;</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
</body>
</html>